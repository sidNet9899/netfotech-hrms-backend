package com.hrms.generalsetupservice.dto;

import lombok.Data;

@Data
public class DepartmentDto {
	
	private Long id;
    private Long companyId;
    private String companyName; // For readability in the response
    private String departmentName;
    private String status;

}
