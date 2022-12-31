package com.example.lokacijaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LokacijaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LokacijaServiceApplication.class, args);
	}

}
