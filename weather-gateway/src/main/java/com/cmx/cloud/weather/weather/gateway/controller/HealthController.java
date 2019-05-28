package com.cmx.cloud.weather.weather.gateway.controller;

import com.cmx.cloud.weather.weather.gateway.controller.service.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cmx
 * @Date 2019/5/26 19:55
 */
@RestController
public class HealthController {

    @Autowired
    private WeatherInfoService weatherInfoService;

    @RequestMapping("/health")
    public String health(){
        return "success";
    }

    @RequestMapping("/test")
    public ResponseEntity test(@RequestParam String cityCode){
        return new ResponseEntity(weatherInfoService.listCityCode(cityCode), HttpStatus.OK);
    }

}
