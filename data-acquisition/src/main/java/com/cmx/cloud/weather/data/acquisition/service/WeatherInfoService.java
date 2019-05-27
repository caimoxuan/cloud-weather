package com.cmx.cloud.weather.data.acquisition.service;

import com.cmx.cloud.weather.data.acquisition.config.AmapConfig;
import com.cmx.cloud.weather.data.acquisition.manager.HttpConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cmx
 * @Date 2019/5/28 0:22
 */
@Slf4j
@Service
public class WeatherInfoService {

    @Autowired
    private AmapConfig amapConfig;
    @Autowired
    private HttpConnectionManager httpConnectionManager;

    public String getWeather(){
        String url = amapConfig.getWeatherApi() + "?city=110101&key="+amapConfig.getKey();
        System.out.println(url);
        try {
            return httpConnectionManager.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
