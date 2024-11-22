package com.hrms.generalsetupservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.generalsetupservice.dto.JobTitleDto;
import com.hrms.generalsetupservice.service.JobTitleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobtitle")
public class JobTitleController {
	
	@Autowired
    private JobTitleService jobTitleService;
	
	@PostMapping("/{companyId}")
    public ResponseEntity<JobTitleDto> createJobTitle(
            @PathVariable Long companyId,
            @Valid @RequestBody JobTitleDto jobTitleDto) {
        JobTitleDto createdJobTitle = jobTitleService.createJobTitle(companyId, jobTitleDto);
        return ResponseEntity.ok(createdJobTitle);
    }
	
	@GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobTitleDto>> getJobTitlesByCompanyId(@PathVariable Long companyId) {
        List<JobTitleDto> jobTitles = jobTitleService.getJobTitlesByCompanyId(companyId);
        return ResponseEntity.ok(jobTitles);
    }
	
	@PutMapping("/{companyId}/{jobTitleId}")
    public ResponseEntity<JobTitleDto> updateJobTitle(
            @PathVariable Long companyId,
            @PathVariable Long jobTitleId,
            @RequestBody JobTitleDto jobTitleDto) {
        JobTitleDto updatedJobTitle = jobTitleService.updateJobTitle(companyId, jobTitleId, jobTitleDto);
        return ResponseEntity.ok(updatedJobTitle);
    }
	
	@DeleteMapping("/{jobTitleId}")
    public ResponseEntity<String> deleteJobTitle(@PathVariable Long jobTitleId) {
        jobTitleService.deleteJobTitle(jobTitleId);
        return ResponseEntity.ok("Job Title deleted successfully.");
    }
}
