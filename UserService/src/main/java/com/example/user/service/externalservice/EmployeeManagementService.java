package com.example.user.service.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.user.service.entity.Employee;



@FeignClient(name = "EMPLOYEEMANAGEMENT")
public interface EmployeeManagementService {

    @GetMapping("/api/employees")
    ResponseEntity<List<Employee>> getAllEmployees();

    @GetMapping("/api/employees/{userId}")
    ResponseEntity<List<Employee>> getEmployeesByUserId(@PathVariable("userId") String userId);
    
}
