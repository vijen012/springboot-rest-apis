package com.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@PropertySource(value = { "classpath:config.properties" })
public class UserInfoDataApiAppV2 {
//	private final static Logger log = LoggerFactory.getLogger(UserInfoDataApiAppV2.class);

	public static void main(String[] args) {
		/*
		 * log.error("error->Application about to start");
		 * log.warn("warn->Application about to start");
		 * log.info("info->Application about to start !!!");
		 * log.debug("debug->Application about to start !!!");
		 * log.trace("trace->Application about to start !!!");
		 */

		SpringApplication.run(UserInfoDataApiAppV2.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@Bean
	public Logger createLogger() {
		return LoggerFactory.getLogger(UserInfoDataApiAppV2.class);
	}
}
