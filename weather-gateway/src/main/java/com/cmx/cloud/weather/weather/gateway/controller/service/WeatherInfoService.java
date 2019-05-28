package com.cmx.cloud.weather.weather.gateway.controller.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cmx
 * @Date 2019/5/28 22:07
 */
@Component
@FeignClient(name = "weather-data-acqusition", path = "/city")
public interface WeatherInfoService {

    @RequestMapping(value = "/code/list", method = RequestMethod.GET)
    String listCityCode(@RequestParam("cityName") String cityCode);

}
