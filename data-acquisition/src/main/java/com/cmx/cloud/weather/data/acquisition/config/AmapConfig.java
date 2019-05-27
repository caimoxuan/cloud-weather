package com.cmx.cloud.weather.data.acquisition.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cmx
 * @date 2019/5/27
 */
@Data
@Component
@ConfigurationProperties(prefix = "amap", ignoreInvalidFields = true)
public class AmapConfig {

    /**
     * 高德开发者key
     */
    private String key;

    /**
     * cityCode下载url
     */
    private String cityCodeDownUrl;


}
