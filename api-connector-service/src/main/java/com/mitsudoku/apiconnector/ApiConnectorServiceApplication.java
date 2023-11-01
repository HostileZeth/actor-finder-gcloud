package com.mitsudoku.apiconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiConnectorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiConnectorServiceApplication.class, args);
	}

}
