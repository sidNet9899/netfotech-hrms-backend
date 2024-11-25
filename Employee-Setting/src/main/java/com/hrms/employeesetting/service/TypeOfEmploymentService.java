package com.hrms.employeesetting.service;

import com.hrms.employeesetting.entity.TypeOfEmployment;
import com.hrms.employeesetting.repository.TypeOfEmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfEmploymentService {

    @Autowired
    private TypeOfEmploymentRepository repository;

    // Add a new Type of Employment
    public TypeOfEmployment addTypeOfEmployment(TypeOfEmployment type) {
        return repository.save(type);
    }

    // Get all Types of Employment
    public List<TypeOfEmployment> getAllTypes() {
        return repository.findAll();
    }

    // Get all Active/Inactive Types
    public List<TypeOfEmployment> getTypesByStatus(String status) {
        return repository.findByStatus(status);
    }

    // Update a Type of Employment
    public TypeOfEmployment updateTypeOfEmployment(Long id, TypeOfEmployment updatedType) {
        TypeOfEmployment existingType = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        existingType.setStatusName(updatedType.getStatusName());
        existingType.setStatus(updatedType.getStatus());
        return repository.save(existingType);
    }

    // Delete a Type of Employment
    public void deleteTypeOfEmployment(Long id) {
        repository.deleteById(id);
    }
}