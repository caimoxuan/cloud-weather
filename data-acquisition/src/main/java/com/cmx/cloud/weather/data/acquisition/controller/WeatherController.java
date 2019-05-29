package com.cmx.cloud.weather.data.acquisition.controller;

import com.cmx.cloud.weather.data.acquisition.service.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cmx
 * @date 2019/5/29
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherInfoService weatherInfoService;

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String getWeather(@RequestParam("cityCode") String cityCode){
        return  weatherInfoService.getWeather(cityCode);
    }



}
