package com.cmx.cloud.weather.data.acquisition.service;

import com.cmx.cloud.weather.data.acquisition.config.AmapConfig;
import com.cmx.cloud.weather.data.acquisition.manager.HttpConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author cmx
 * @Date 2019/5/26 14:27
 */
@Slf4j
@Service
public class CityCodeParseService {

    @Autowired
    private HttpConnectionManager httpConnectionManager;
    @Autowired
    private AmapConfig amapConfig;



    public Map<String, String> getCityCode() throws Exception {

        String s = httpConnectionManager.doGet(amapConfig.getCityCodeDownUrl());

        return null;
    }



}
