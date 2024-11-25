package com.hrms.employeesetting.service;

import com.hrms.employeesetting.entity.EducationStandard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.hrms.employeesetting.repository.EducationStandardRepository;

@Service
public class EducationStandardService {

    @Autowired
    private EducationStandardRepository repository;

    // Get all education standards
    public List<EducationStandard> getAllStandards() {
        return repository.findAll();
    }

    // Create a new education standard
    @Transactional
    public EducationStandard createStandard(EducationStandard standard) {
        if (standard.getEducationStandard() == null || standard.getEducationStandard().isEmpty()) {
            return null;  // Invalid standard, return null (you can handle this as needed later)
        }
        return repository.save(standard);  // Save and return the created standard
    }

    // Update an existing education standard
    @Transactional
    public EducationStandard updateStandard(Long id, EducationStandard updatedStandard) {
        Optional<EducationStandard> existingStandard = repository.findById(id);
        if (existingStandard.isPresent()) {
            EducationStandard standard = existingStandard.get();
            // Update the existing standard with new values
            standard.setEducationStandard(updatedStandard.getEducationStandard());
            standard.setStatus(updatedStandard.getStatus());
            return repository.save(standard);  // Save and return the updated standard
        }
        return null;  // Return null if standard not found
    }

    // Delete an education standard
    @Transactional
    public void deleteStandard(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);  // Delete the standard by ID if exists
        }
    }
}
