package com.cmx.cloud.weather.nameserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NameServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NameServerApplication.class, args);
	}

}
