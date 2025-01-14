package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	List<Employee> findByUserId(String userId);

}
