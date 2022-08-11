package com.c2_g4_discount_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class C2G4DiscountMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(C2G4DiscountMsApplication.class, args);
	}

}
