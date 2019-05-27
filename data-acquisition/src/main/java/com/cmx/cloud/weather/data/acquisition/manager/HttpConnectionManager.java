package com.cmx.cloud.weather.data.acquisition.manager;

import com.cmx.cloud.weather.data.acquisition.config.HttpConfig;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cmx
 * @Date 2019/5/26 14:34
 */
@Slf4j
@Component
public class HttpConnectionManager {

    private static final String HTTP_THREAD_NAME = "httpClient-Thread-";
    private static PoolingHttpClientConnectionManager manager;

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    /**
     * API http请求头中描述的body数据类型
     */
    private static final String API_CONTENT_TYPE = "application/json;charset=utf-8";

    private RequestConfig requestConfig;

    private Gson gson = new Gson();

    HttpConnectionManager(HttpConfig httpConfig){

        //对http请求进行超时配置
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(httpConfig.getHttpConnectTimeout()).
                setConnectTimeout(httpConfig.getHttpConnectTimeout()).setSocketTimeout(httpConfig.getHttpSocketTimeout()).build();

        //初始化poolManager
        manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(300);
        manager.setDefaultMaxPerRoute(50);

        //开启失效定时检测
            ScheduledExecutorService monitorExecutor = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {

            AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,  HTTP_THREAD_NAME + threadCount.getAndIncrement());
            }
        });

        monitorExecutor.scheduleAtFixedRate(() -> {
            //检测异常连接并关闭
            manager.closeExpiredConnections();
            //检测超时空闲的连接并关闭
            manager.closeIdleConnections(httpConfig.getHttpConnectTimeout(), TimeUnit.MILLISECONDS);

        }, httpConfig.getHttpMonitorInterval(), httpConfig.getHttpMonitorInterval(), TimeUnit.MILLISECONDS);

    }

    public String doPostRequest(Object object, String url){

        CloseableHttpClient httpClient = getCloseClient();

        HttpPost httpPost = new HttpPost(url);

        //3 将body在放入请求中 请求的json信息
        String jsonMessage = gson.toJson(object);

        StringEntity httpEntity = new StringEntity(jsonMessage,  DEFAULT_CHARSET);
        httpEntity.setContentType(API_CONTENT_TYPE);
        httpEntity.setContentEncoding(DEFAULT_CHARSET.name());
        httpPost.setEntity(httpEntity);

        //4 发起请求 封装返回 (他的验签参数在header中, 返回的结果体在entity中)
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                String data = EntityUtils.toString(response.getEntity(),  DEFAULT_CHARSET);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    return data;
                }
            }
        } catch (Exception e) {
            log.info("http post get exception : {}", e);
        }

        return null;
    }


    public String doGet(String url) throws Exception {

        CloseableHttpClient httpClient = getCloseClient();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                 return EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }


    private CloseableHttpClient getCloseClient(){
        return HttpClients.custom()
                .setConnectionManager(manager).setDefaultRequestConfig(requestConfig).build();
    }

}
