package com.example.centerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CenterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CenterServerApplication.class, args);
	}

}
