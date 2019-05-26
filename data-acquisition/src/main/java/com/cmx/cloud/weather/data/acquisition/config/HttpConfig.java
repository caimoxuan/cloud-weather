package com.cmx.cloud.weather.data.acquisition.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cmx
 * @Date 2019/5/26 14:37
 */
@Data
@Component
@ConfigurationProperties(prefix = "httpclient", ignoreInvalidFields = true)
public class HttpConfig {

    private Integer httpConnectTimeout;

    private Integer httpSocketTimeout;

    private Integer httpMaxPoolSize;

    private Integer httpMonitorInterval;

}
