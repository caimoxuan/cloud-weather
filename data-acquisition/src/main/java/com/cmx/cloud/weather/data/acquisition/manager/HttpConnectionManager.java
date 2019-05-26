package com.cmx.cloud.weather.data.acquisition.manager;

import com.cmx.cloud.weather.data.acquisition.config.HttpConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cmx
 * @Date 2019/5/26 14:34
 */
@Component
public class HttpConnectionManager {

    private static final String HTTP_THREAD_NAME = "httpClient-Thread-";
    private static PoolingHttpClientConnectionManager manager; //连接池管理类
    private static ScheduledExecutorService monitorExecutor;
    private static Map<String, CloseableHttpClient> httpClientMap = new ConcurrentHashMap<>();

    private RequestConfig requestConfig;

    HttpConnectionManager(HttpConfig httpConfig){

        System.out.println(httpConfig);

        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(httpConfig.getHttpConnectTimeout()).
                setConnectTimeout(httpConfig.getHttpConnectTimeout()).setSocketTimeout(httpConfig.getHttpSocketTimeout()).build();
        monitorExecutor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 8);
    }

    public CloseableHttpClient getHttpClient(String url){

        String host = getHost(url);
        if(host == null){
            return null;
        }
        CloseableHttpClient httpClient = httpClientMap.get(host);



        return null;
    }


    private String getHost(String url) {
        if (url == null || url.trim().equals("")) {
            return "";
        }
        String host = "";
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+(:\\d*)?");
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            host = matcher.group();
        }
        return host;
    }

}
