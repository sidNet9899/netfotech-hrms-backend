package com.hrms.employeesetting.service;

import com.hrms.employeesetting.dto.EmployeeEducationSettingDTO;
import com.hrms.employeesetting.entity.EmployeeEducationSetting;
import com.hrms.employeesetting.mapper.EmployeeEducationSettingMapper;
import com.hrms.employeesetting.repository.EmployeeEducationSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeEducationSettingService {

    @Autowired
    private EmployeeEducationSettingRepository repository;

    // Get all records
    public List<EmployeeEducationSettingDTO> getAllSettings() {
        return repository.findAll().stream()
                .map(EmployeeEducationSettingMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Save a new record
    public EmployeeEducationSettingDTO createSetting(EmployeeEducationSettingDTO dto) {
        EmployeeEducationSetting entity = EmployeeEducationSettingMapper.toEntity(dto);
        EmployeeEducationSetting savedEntity = repository.save(entity);
        return EmployeeEducationSettingMapper.toDTO(savedEntity);
    }

    // Update an existing record
    public EmployeeEducationSettingDTO updateSetting(Long id, EmployeeEducationSettingDTO dto) {
        EmployeeEducationSetting existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));

        existingEntity.setStandard(dto.getStandard());
        existingEntity.setCourseName(dto.getCourseName());
        existingEntity.setStatus(dto.getStatus());

        EmployeeEducationSetting updatedEntity = repository.save(existingEntity);
        return EmployeeEducationSettingMapper.toDTO(updatedEntity);
    }

    // Delete a record
    public void deleteSetting(Long id) {
        repository.deleteById(id);
    }
}