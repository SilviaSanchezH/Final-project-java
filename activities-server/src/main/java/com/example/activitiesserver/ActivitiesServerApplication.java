package com.example.activitiesserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ActivitiesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiesServerApplication.class, args);
	}

}
