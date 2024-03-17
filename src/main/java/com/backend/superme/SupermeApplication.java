package com.backend.superme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SupermeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermeApplication.class, args);
	}

}