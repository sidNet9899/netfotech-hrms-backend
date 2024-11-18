package com.hrms.generalsetupservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.generalsetupservice.dto.CompanyDto;
import com.hrms.generalsetupservice.entity.Company;
import com.hrms.generalsetupservice.exception.ResourceNotFoundException;
import com.hrms.generalsetupservice.repository.CompanyRepository;

import jakarta.validation.Valid;

@Service
public class CompanyService {
	@Autowired
    private CompanyRepository companyRepository;
	
	public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
	
	public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
        return convertToDto(company);
    }
	
	public CompanyDto createCompany(@Valid CompanyDto companyDto) {
        validateUniqueFields(companyDto);
        Company company = convertToEntity(companyDto);
        return convertToDto(companyRepository.save(company));
    }
	
	public CompanyDto updateCompany(Long id, @Valid CompanyDto companyDto) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        // Update only the fields provided in companyDto
        if (companyDto.getCompanyName() != null) existingCompany.setCompanyName(companyDto.getCompanyName());
        if (companyDto.getCompanyCode() != null) existingCompany.setCompanyCode(companyDto.getCompanyCode());
        if (companyDto.getAddress() != null) existingCompany.setAddress(companyDto.getAddress());
        if (companyDto.getWebsite() != null) existingCompany.setWebsite(companyDto.getWebsite());
        if (companyDto.getTelephone() != null) existingCompany.setTelephone(companyDto.getTelephone());
        if (companyDto.getEmail() != null) existingCompany.setEmail(companyDto.getEmail());
        if (companyDto.getSkypeId() != null) existingCompany.setSkypeId(companyDto.getSkypeId());
        if (companyDto.getCountry() != null) existingCompany.setCountry(companyDto.getCountry());
        if (companyDto.getTimezone() != null) existingCompany.setTimezone(companyDto.getTimezone());
        if (companyDto.getCurrency() != null) existingCompany.setCurrency(companyDto.getCurrency());
        if (companyDto.getPayType() != null) existingCompany.setPayType(companyDto.getPayType());
        if (companyDto.getDateTimeFormat() != null) existingCompany.setDateTimeFormat(companyDto.getDateTimeFormat());
        if (companyDto.getDisplayName() != null) existingCompany.setDisplayName(companyDto.getDisplayName());

        return convertToDto(companyRepository.save(existingCompany));
    }
	
	public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
	
	
	private void validateUniqueFields(CompanyDto companyDto) {
        if (companyRepository.existsByCompanyName(companyDto.getCompanyName())) {
            throw new RuntimeException("Company name already exists");
        }
        if (companyRepository.existsByCompanyCode(companyDto.getCompanyCode())) {
            throw new RuntimeException("Company code already exists");
        }
        if (companyRepository.existsByEmail(companyDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        // Add more validations as needed
	}
	private CompanyDto convertToDto(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setCompanyName(company.getCompanyName());
        dto.setCompanyCode(company.getCompanyCode());
        dto.setAddress(company.getAddress());
        dto.setWebsite(company.getWebsite());
        dto.setTelephone(company.getTelephone());
        dto.setEmail(company.getEmail());
        dto.setSkypeId(company.getSkypeId());
        dto.setCountry(company.getCountry());
        dto.setTimezone(company.getTimezone());
        dto.setCurrency(company.getCurrency());
        dto.setPayType(company.getPayType());
        dto.setDateTimeFormat(company.getDateTimeFormat());
        dto.setDisplayName(company.getDisplayName());
        return dto;
    }
	
	private Company convertToEntity(CompanyDto dto) {
		Company company = new Company();
		company.setCompanyName(dto.getCompanyName());
        company.setCompanyCode(dto.getCompanyCode());
        company.setAddress(dto.getAddress());
        company.setWebsite(dto.getWebsite());
        company.setTelephone(dto.getTelephone());
        company.setEmail(dto.getEmail());
        company.setSkypeId(dto.getSkypeId());
        company.setCountry(dto.getCountry());
        company.setTimezone(dto.getTimezone());
        company.setCurrency(dto.getCurrency());
        company.setPayType(dto.getPayType());
        company.setDateTimeFormat(dto.getDateTimeFormat());
        company.setDisplayName(dto.getDisplayName());
		return company;
	}
	


}