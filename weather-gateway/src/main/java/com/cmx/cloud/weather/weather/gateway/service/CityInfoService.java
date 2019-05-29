package com.cmx.cloud.weather.weather.gateway.service;

import com.cmx.cloud.weather.weather.gateway.fallback.CityCodeFallbackFactory;
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
@FeignClient(name = "weather-data-acqusition",  fallbackFactory = CityCodeFallbackFactory.class)
public interface CityInfoService {

    /**
     * 获取匹配的城市信息
     * @param cityCode 城市匹配关键字
     * @param startPage 开始页面
     * @param pageSize 页面大小
     * @return
     */
    @RequestMapping(value = "/city/code/list", method = RequestMethod.GET)
    String listCityCode(@RequestParam("cityName") String cityCode,
                        @RequestParam("startPage") int startPage,
                        @RequestParam("pageSize") int pageSize);

}
