package com.netfotech.settingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netfotech.settingservice.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	
}
