package com.example.employeemanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://localhost:8080/api/auth")  // Replace with Auth Service details
public interface AuthServiceFeignClient {

    @GetMapping("/validate")
    boolean validateToken(@RequestHeader("Authorization") String token);
    
   
}
