package com.hrms.generalsetupservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.generalsetupservice.dto.JobTitleDto;
import com.hrms.generalsetupservice.entity.Company;
import com.hrms.generalsetupservice.entity.JobTitle;
import com.hrms.generalsetupservice.exception.ResourceAlreadyExistsException;
import com.hrms.generalsetupservice.exception.ResourceNotFoundException;
import com.hrms.generalsetupservice.repository.CompanyRepository;
import com.hrms.generalsetupservice.repository.JobTitleRepository;

@Service
public class JobTitleService {
	
	@Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    public JobTitleDto createJobTitle(Long companyId, JobTitleDto jobTitleDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + companyId));

        jobTitleRepository.findByCompanyIdAndJobTitle(companyId, jobTitleDto.getJobTitle())
                .ifPresent(existing -> {
                    throw new ResourceAlreadyExistsException("Job Title already exists for the company.");
                });

        JobTitle jobTitle = mapToEntity(jobTitleDto);
        jobTitle.setCompany(company);

        JobTitle savedJobTitle = jobTitleRepository.save(jobTitle);
        return mapToDto(savedJobTitle);
    }
    
    public List<JobTitleDto> getJobTitlesByCompanyId(Long companyId) {
        List<JobTitle> jobTitles = jobTitleRepository.findByCompanyId(companyId);
        return jobTitles.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    public JobTitleDto updateJobTitle(Long companyId, Long jobTitleId, JobTitleDto jobTitleDto) {
        JobTitle existingJobTitle = jobTitleRepository.findById(jobTitleId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Title not found with ID: " + jobTitleId));

        if (!existingJobTitle.getCompany().getId().equals(companyId)) {
            throw new ResourceNotFoundException("Job Title does not belong to the specified company.");
        }

        if (jobTitleDto.getJobTitle() != null) {
            existingJobTitle.setJobTitle(jobTitleDto.getJobTitle());
        }

        if (jobTitleDto.getJobLevel() != null) {
            existingJobTitle.setJobLevel(jobTitleDto.getJobLevel());
        }

        if (jobTitleDto.getStatus() != null) {
            existingJobTitle.setStatus(jobTitleDto.getStatus());
        }

        JobTitle updatedJobTitle = jobTitleRepository.save(existingJobTitle);
        return mapToDto(updatedJobTitle);
    }
    
    public void deleteJobTitle(Long jobTitleId) {
        JobTitle jobTitle = jobTitleRepository.findById(jobTitleId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Title not found with ID: " + jobTitleId));

        jobTitleRepository.delete(jobTitle);
    }
    
 // Helper method to map DTO to Entity
    private JobTitle mapToEntity(JobTitleDto jobTitleDto) {
        JobTitle jobTitle = new JobTitle();
        jobTitle.setJobTitle(jobTitleDto.getJobTitle());
        jobTitle.setJobLevel(jobTitleDto.getJobLevel());
        jobTitle.setStatus(jobTitleDto.getStatus());
        return jobTitle;
    }
    
 // Helper method to map Entity to DTO
    private JobTitleDto mapToDto(JobTitle jobTitle) {
        JobTitleDto jobTitleDto = new JobTitleDto();
        jobTitleDto.setId(jobTitle.getId());
        jobTitleDto.setJobTitle(jobTitle.getJobTitle());
        jobTitleDto.setJobLevel(jobTitle.getJobLevel());
        jobTitleDto.setStatus(jobTitle.getStatus());
        return jobTitleDto;
    }
}
