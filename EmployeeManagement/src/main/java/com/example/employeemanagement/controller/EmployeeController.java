package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.feign.AuthServiceFeignClient;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthServiceFeignClient authServiceFeignClient;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestHeader("Authorization") String token) {
        if (authServiceFeignClient.validateToken(token)) {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
    }

    @GetMapping("/{username}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String username, @RequestHeader("Authorization") String token) {
        if (authServiceFeignClient.validateToken(token)) {
            Employee employee = employeeService.getEmployeeByUsername(username);
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee, @RequestHeader("Authorization") String token) {
        if (authServiceFeignClient.validateToken(token)) {
            if (employeeService.getEmployeeByUsername(employee.getUsername()) == null) {
                Employee savedEmployee = employeeService.saveEmployee(employee);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee); // 201 Created
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict: Employee already exists
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
    }

    @PutMapping("/{username}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String username, @RequestBody Employee employee,
                                                   @RequestHeader("Authorization") String token) {
        if (authServiceFeignClient.validateToken(token)) {
            Employee updatedEmployee = employeeService.updateEmployee(username, employee);
            if (updatedEmployee != null) {
                return ResponseEntity.ok(updatedEmployee); // 200 OK
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String username, @RequestHeader("Authorization") String token) {
        if (authServiceFeignClient.validateToken(token)) {
            employeeService.deleteEmployee(username);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
    }
}
