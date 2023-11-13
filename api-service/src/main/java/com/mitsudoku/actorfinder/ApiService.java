package com.mitsudoku.actorfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.mitsudoku.actorfinder.entity")
//@ComponentScan("com.mitsudoku.actorfinder.*")
//@EnableJpaRepositories("com.mitsudoku.actorfinder.repository")
public class ApiService {

	public static void main(String[] args) {
		SpringApplication.run(ApiService.class, args);
	}
}
