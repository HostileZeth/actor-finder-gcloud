package com.mitsudoku.actorfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.mitsudoku.actorfinder.entity")
//@ComponentScan("com.mitsudoku.actorfinder.*")
//@EnableJpaRepositories("com.mitsudoku.actorfinder.repository")
public class ActorFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActorFinderApplication.class, args);
	}
}
