package com.cmx.cloud.weather.data.acquisition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableConfigurationProperties
public class DataAcquisitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAcquisitionApplication.class, args);
	}

}
