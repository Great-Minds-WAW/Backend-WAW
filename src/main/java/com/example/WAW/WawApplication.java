package com.example.WAW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WawApplication {

	public static void main(String[] args) {
		SpringApplication.run(WawApplication.class, args);
	}

}
