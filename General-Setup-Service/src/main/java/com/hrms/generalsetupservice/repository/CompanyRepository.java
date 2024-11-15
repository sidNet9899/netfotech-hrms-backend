package com.hrms.generalsetupservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.generalsetupservice.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
}
