package com.cmx.cloud.weather.weather.gateway.fallback;

import com.cmx.cloud.weather.weather.gateway.service.CityInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cmx
 * @date 2019/5/29
 */
@Component
public class CityCodeFallbackFactory  implements FallbackFactory<CityInfoService>{


    @Override
    public CityInfoService create(Throwable throwable) {
        return (cityCode, startPage, pageSize) -> {
            Map<String, String> result = new HashMap<>(2);
            result.put("code", "401");
            result.put("result", "protected by hystrix");
            ObjectMapper objectMapper = new ObjectMapper();
            String s = null;
            try {
                s = objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return s;
        };
    }


}
