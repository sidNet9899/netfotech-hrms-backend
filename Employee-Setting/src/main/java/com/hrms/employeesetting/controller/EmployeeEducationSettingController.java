package com.hrms.employeesetting.controller;

import com.hrms.employeesetting.dto.EmployeeEducationSettingDTO;
import com.hrms.employeesetting.service.EmployeeEducationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-education-settings")
public class EmployeeEducationSettingController {

    @Autowired
    private EmployeeEducationSettingService service;

    // Get all settings
    @GetMapping
    public ResponseEntity<List<EmployeeEducationSettingDTO>> getAllSettings() {
        return ResponseEntity.ok(service.getAllSettings());
    }

    // Create a new setting
    @PostMapping
    public ResponseEntity<EmployeeEducationSettingDTO> createSetting(@RequestBody EmployeeEducationSettingDTO dto) {
        return ResponseEntity.ok(service.createSetting(dto));
    }

    // Update a setting
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEducationSettingDTO> updateSetting(
            @PathVariable Long id, @RequestBody EmployeeEducationSettingDTO dto) {
        return ResponseEntity.ok(service.updateSetting(id, dto));
    }

    // Delete a setting
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable Long id) {
        service.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}