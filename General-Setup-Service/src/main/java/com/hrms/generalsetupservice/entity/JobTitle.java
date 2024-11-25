package com.hrms.generalsetupservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class JobTitle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
	
	@NotBlank(message = "Job Title Cannot be blank")
	private String jobTitle;
	
	@NotBlank(message = "Job Level Cannot be blank")
	private String jobLevel;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status cannot be null")
	private Status status;
	
	
}
