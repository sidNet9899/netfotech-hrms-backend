package com.hrms.employeesetting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.employeesetting.entity.EducationStandard;
import com.hrms.employeesetting.service.EducationStandardService;

@RestController
@RequestMapping("/api/education-standards")
public class EducationStandardController {

    @Autowired
    private EducationStandardService service;

    // Get all education standards
    @GetMapping
    public List<EducationStandard> getAllStandards() {
        return service.getAllStandards();
    }

    // Create a new education standard
    @PostMapping
    public ResponseEntity<EducationStandard> createStandard(@RequestBody EducationStandard standard) {
        EducationStandard createdStandard = service.createStandard(standard);
        if (createdStandard != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStandard);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Handle case where standard is not created (e.g., invalid input)
    }

    // Update an existing education standard
    @PutMapping("/{id}")
    public ResponseEntity<EducationStandard> updateStandard(@PathVariable Long id, @RequestBody EducationStandard updatedStandard) {
        EducationStandard standard = service.updateStandard(id, updatedStandard);
        if (standard != null) {
            return ResponseEntity.ok(standard);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if the standard is not found
    }

    // Delete an education standard
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStandard(@PathVariable Long id) {
        service.deleteStandard(id);
        return ResponseEntity.noContent().build();  // Return 204 No Content status
    }
}
