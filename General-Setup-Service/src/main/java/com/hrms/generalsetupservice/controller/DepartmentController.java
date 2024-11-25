package com.hrms.generalsetupservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.generalsetupservice.dto.DepartmentDto;
import com.hrms.generalsetupservice.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
    private DepartmentService departmentService;

    @PostMapping("/create/{companyId}")
    public ResponseEntity<DepartmentDto> createDepartment(@PathVariable Long companyId,@RequestBody DepartmentDto departmentDTO) {
        DepartmentDto createdDepartment = departmentService.createDepartment(companyId,departmentDTO);
        return ResponseEntity.ok(createdDepartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<DepartmentDto>> getDepartmentsByCompanyId(@PathVariable Long companyId) {
        List<DepartmentDto> departments = departmentService.getDepartmentsByCompanyId(companyId);
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDTO) {
        DepartmentDto updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully.");
    }
}
