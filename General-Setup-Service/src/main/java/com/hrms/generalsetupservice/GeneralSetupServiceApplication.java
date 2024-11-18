package com.hrms.generalsetupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GeneralSetupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralSetupServiceApplication.class, args);
	}

}
