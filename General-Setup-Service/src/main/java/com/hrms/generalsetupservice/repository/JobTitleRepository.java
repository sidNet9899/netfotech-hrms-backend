package com.hrms.generalsetupservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.generalsetupservice.entity.JobTitle;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long>{
	
	 List<JobTitle> findByCompanyId(Long companyId);

	 Optional<JobTitle> findByCompanyIdAndJobTitle(Long companyId, String jobTitle);

}
