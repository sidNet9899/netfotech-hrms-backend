package com.hrms.employeesetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
public class EmployeeSettingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSettingApplication.class, args);
	}

}
