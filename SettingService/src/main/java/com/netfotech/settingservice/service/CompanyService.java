package com.netfotech.settingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfotech.settingservice.dto.CompanyDto;
import com.netfotech.settingservice.entity.Company;
import com.netfotech.settingservice.entity.Status;
import com.netfotech.settingservice.exception.NotFoundException;
import com.netfotech.settingservice.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company createCompany(CompanyDto companyDTO) {
		Company company = new Company();
		updateCompanyEntity(company, companyDTO);
		return companyRepository.save(company);
	}
	public Company updateCompany(Long id, CompanyDto companyDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found"));
        updateCompanyEntity(company, companyDTO);
        return companyRepository.save(company);
    }
	
	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
	}
	
	public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }
	

	private void updateCompanyEntity(Company company, CompanyDto companyDTO) {
		company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyCode(companyDTO.getCompanyCode());
        company.setTaxId(companyDTO.getTaxId());
        company.setZipCode(companyDTO.getZipCode());
        company.setAddress1(companyDTO.getAddress1());
        company.setTelephone(companyDTO.getTelephone());
        company.setEmail(companyDTO.getEmail());
        company.setSkypeId(companyDTO.getSkypeId());
        company.setWebsite(companyDTO.getWebsite());
        company.setCountry(companyDTO.getCountry());
        company.setTimeZone(companyDTO.getTimeZone());
        company.setCurrency(companyDTO.getCurrency());
        company.setPayType(companyDTO.getPayType());
        company.setDateTimeFormat(companyDTO.getDateTimeFormat());
        company.setNumberFormat(companyDTO.getNumberFormat());
        company.setDisplayName(companyDTO.getDisplayName());
        company.setStatus(companyDTO.getStatus() != null ? companyDTO.getStatus() : Status.ACTIVE);
		
	}
}
