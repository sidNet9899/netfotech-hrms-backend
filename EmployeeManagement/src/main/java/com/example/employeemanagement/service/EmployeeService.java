package com.example.employeemanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findById(username).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String username, Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeByUsername(username);
        if (existingEmployee != null) {
            updatedEmployee.setUsername(username);
            return employeeRepository.save(updatedEmployee);
        }
        return null;
    }

    public void deleteEmployee(String username) {
        employeeRepository.deleteById(username);
    }
}
