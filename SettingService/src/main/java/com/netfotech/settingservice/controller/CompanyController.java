package com.netfotech.settingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.netfotech.settingservice.dto.CompanyDto;
import com.netfotech.settingservice.entity.Company;
import com.netfotech.settingservice.service.CompanyService;

public class CompanyController {
	
	@Autowired
    private CompanyService companyService;
	
	// Only Super Admin and Admin can access
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PostMapping
    public Company createCompany(@Validated @RequestBody CompanyDto companyDTO) {
        return companyService.createCompany(companyDTO);
    }
    
    
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @Validated @RequestBody CompanyDto companyDTO) {
        return companyService.updateCompany(id, companyDTO);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

}
