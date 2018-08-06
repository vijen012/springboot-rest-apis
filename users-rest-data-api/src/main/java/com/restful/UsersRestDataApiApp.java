package com.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:config.properties" })
public class UsersRestDataApiApp {
	private final static Logger log = LoggerFactory.getLogger(UsersRestDataApiApp.class);

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		// Do any additional configuration here
//		return builder.build();
//	}

	public static void main(String[] args) {
		log.info("Application about start !!!");
		log.debug("Application about start !!!");
		SpringApplication.run(UsersRestDataApiApp.class, args);
	}
}
