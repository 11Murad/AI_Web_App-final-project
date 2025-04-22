package com.spring.aiwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AIWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AIWebAppApplication.class, args);
	}

}
