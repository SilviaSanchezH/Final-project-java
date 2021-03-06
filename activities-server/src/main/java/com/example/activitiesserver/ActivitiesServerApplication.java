package com.example.activitiesserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ActivitiesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiesServerApplication.class, args);
	}

}
