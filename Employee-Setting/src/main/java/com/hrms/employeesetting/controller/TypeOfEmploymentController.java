package com.hrms.employeesetting.controller;

import com.hrms.employeesetting.entity.TypeOfEmployment;
import com.hrms.employeesetting.service.TypeOfEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-of-employment")
public class TypeOfEmploymentController {

    @Autowired
    private TypeOfEmploymentService service;

    // Create a new type of employment
    @PostMapping
    public TypeOfEmployment createType(@RequestBody TypeOfEmployment type) {
        return service.addTypeOfEmployment(type);
    }

    // Get all types of employment
    @GetMapping
    public List<TypeOfEmployment> getAllTypes() {
        return service.getAllTypes();
    }

    // Get types by status (e.g., Active, Inactive)
    @GetMapping("/status/{status}")
    public List<TypeOfEmployment> getTypesByStatus(@PathVariable String status) {
        return service.getTypesByStatus(status);
    }

    // Update a type of employment
    @PutMapping("/{id}")
    public TypeOfEmployment updateType(@PathVariable Long id, @RequestBody TypeOfEmployment updatedType) {
        return service.updateTypeOfEmployment(id, updatedType);
    }

    // Delete a type of employment
    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id) {
        service.deleteTypeOfEmployment(id);
    }
}
