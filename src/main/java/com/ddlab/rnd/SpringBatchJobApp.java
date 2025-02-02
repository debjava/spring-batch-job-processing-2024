package com.ddlab.rnd;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchJobApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchJobApp.class, args);
	}
}
