package com.cmx.cloud.weather.data.acquisition.controller;

import com.cmx.cloud.weather.data.acquisition.es.model.CityCodeDocument;
import com.cmx.cloud.weather.data.acquisition.es.service.CityCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cmx
 * @Date 2019/5/26 16:21
 */
@RestController
@RequestMapping("/city")
public class CityCodeController {

    @Autowired
    private CityCodeService cityCodeService;


    @RequestMapping( value = "/code/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CityCodeDocument> cityCode(@RequestParam String cityName){
        return cityCodeService.matchCity(cityName);
    }


}
