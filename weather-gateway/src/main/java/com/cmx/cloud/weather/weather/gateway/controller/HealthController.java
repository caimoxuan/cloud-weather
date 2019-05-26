package com.cmx.cloud.weather.weather.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cmx
 * @Date 2019/5/26 19:55
 */
@RestController
public class HealthController {

    @RequestMapping("/health")

    public String health(){
        return "success";
    }

}
