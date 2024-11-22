package com.hrms.generalsetupservice.dto;

import com.hrms.generalsetupservice.entity.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobTitleDto {
	private Long id;

    @NotBlank(message = "Job Title cannot be blank")
    private String jobTitle;

    @NotBlank(message = "Job Level cannot be blank")
    private String jobLevel;

    @NotNull(message = "Status cannot be null")
    private Status status;
}
