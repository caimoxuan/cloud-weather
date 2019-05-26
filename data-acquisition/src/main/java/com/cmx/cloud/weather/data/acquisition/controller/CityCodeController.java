package com.cmx.cloud.weather.data.acquisition.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cmx
 * @Date 2019/5/26 16:21
 */
@RestController
@RequestMapping("/city")
public class CityCodeController {


    @RequestMapping("/code/list")
    public Map cityCode(){

        Map<String, Integer> cityMap = new HashMap<>();
        cityMap.put("杭州", 33010001);

        return cityMap;
    }


}
