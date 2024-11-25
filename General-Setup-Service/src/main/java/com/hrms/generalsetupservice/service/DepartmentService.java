package com.hrms.generalsetupservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.generalsetupservice.dto.DepartmentDto;
import com.hrms.generalsetupservice.entity.Company;
import com.hrms.generalsetupservice.entity.Department;
import com.hrms.generalsetupservice.entity.Status;
import com.hrms.generalsetupservice.exception.ResourceAlreadyExistsException;
import com.hrms.generalsetupservice.exception.ResourceNotFoundException;
import com.hrms.generalsetupservice.repository.CompanyRepository;
import com.hrms.generalsetupservice.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    public DepartmentDto createDepartment(Long companyId,DepartmentDto departmentDTO) {
    	// Fetch the company by ID
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + companyId));

     // Check if a department with the same name already exists for this company
        departmentRepository.findByDepartmentNameAndCompanyId(departmentDTO.getDepartmentName(), companyId)
                .ifPresent(department -> {
                    throw new ResourceAlreadyExistsException("Department with name '" + departmentDTO.getDepartmentName() + "' already exists in this company.");
                });

        Department department = new Department();
        department.setCompany(company);
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setStatus(Status.valueOf(departmentDTO.getStatus()));

        Department savedDepartment = departmentRepository.save(department);

        return mapToDTO(savedDepartment);
    }

    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        return mapToDTO(department);
    }

    public List<DepartmentDto> getDepartmentsByCompanyId(Long companyId) {
        return departmentRepository.findByCompanyId(companyId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));

        if (departmentDTO.getDepartmentName() != null) {
            departmentRepository.findByDepartmentNameAndCompanyId(departmentDTO.getDepartmentName(), department.getCompany().getId())
                    .ifPresent(existingDepartment -> {
                        if (!existingDepartment.getId().equals(id)) {
                            throw new ResourceAlreadyExistsException("Another department with the name '" + departmentDTO.getDepartmentName() + "' already exists in this company.");
                        }
                    });
            department.setDepartmentName(departmentDTO.getDepartmentName());
        }

        if (departmentDTO.getStatus() != null) {
            department.setStatus(Status.valueOf(departmentDTO.getStatus()));
        }

        Department updatedDepartment = departmentRepository.save(department);
        return mapToDTO(updatedDepartment);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        departmentRepository.delete(department);
    }

    private DepartmentDto mapToDTO(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setCompanyId(department.getCompany().getId());
        dto.setCompanyName(department.getCompany().getCompanyName());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setStatus(department.getStatus().toString());
        return dto;
    }

}
