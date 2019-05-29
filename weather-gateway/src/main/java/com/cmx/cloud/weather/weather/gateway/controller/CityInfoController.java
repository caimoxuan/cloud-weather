package com.cmx.cloud.weather.weather.gateway.controller;

import com.cmx.cloud.weather.weather.gateway.service.CityInfoService;
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
public class CityInfoController {

    @Autowired
    private CityInfoService cityInfoService;

    @RequestMapping("/test")
    public ResponseEntity test(@RequestParam("cityCode") String cityCode,
                               @RequestParam("startPage") int startPage,
                               @RequestParam("pageSize") int pageSize){
        return new ResponseEntity(cityInfoService.listCityCode(cityCode, startPage, pageSize), HttpStatus.OK);
    }

}
