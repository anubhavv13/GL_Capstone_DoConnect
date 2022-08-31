package com.wipro.doconnect;

/*
* @Author : 
* Modified last date: 30-08-22
* Description :
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DoConnectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoConnectServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
