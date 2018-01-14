package com.semafors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SemaforsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SemaforsApplication.class, args);
	}
}
