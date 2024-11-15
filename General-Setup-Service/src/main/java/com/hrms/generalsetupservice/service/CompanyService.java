package com.hrms.generalsetupservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.generalsetupservice.entity.Company;
import com.hrms.generalsetupservice.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company updatedCompany) {
        Company existingCompany = getCompanyById(id);
        existingCompany.setCompanyName(updatedCompany.getCompanyName());
        existingCompany.setCompanyCode(updatedCompany.getCompanyCode());
        existingCompany.setAddress(updatedCompany.getAddress());
        existingCompany.setWebsite(updatedCompany.getWebsite());
        existingCompany.setTelephone(updatedCompany.getTelephone());
        existingCompany.setEmail(updatedCompany.getEmail());
        existingCompany.setSkypeId(updatedCompany.getSkypeId());
        existingCompany.setCountry(updatedCompany.getCountry());
        existingCompany.setTimezone(updatedCompany.getTimezone());
        existingCompany.setCurrency(updatedCompany.getCurrency());
        existingCompany.setPayType(updatedCompany.getPayType());
        existingCompany.setDateTimeFormat(updatedCompany.getDateTimeFormat());
        existingCompany.setDisplayName(updatedCompany.getDisplayName());
        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
