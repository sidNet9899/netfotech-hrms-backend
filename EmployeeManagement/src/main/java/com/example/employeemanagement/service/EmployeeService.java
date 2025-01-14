package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {
	
    Employee addEmployee(Employee employee);

    Employee updateEmployee(String employeeId, Employee employee);

    void deleteEmployee(String employeeId);


    List<Employee> getAllEmployees();
    

    // Get employees by userId
    List<Employee> getEmployeesByUserId(String userId);

}
