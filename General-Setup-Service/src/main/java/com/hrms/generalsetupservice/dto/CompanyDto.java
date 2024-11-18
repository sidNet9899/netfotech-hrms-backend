package com.hrms.generalsetupservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyDto {
	@NotBlank(message = "Company name is mandatory")
	private String companyName;
	
	@NotBlank(message = "Company code is mandatory")
    @Size(max = 10, message = "Company code should not exceed 10 characters")
    private String companyCode;
    private String address;
    private String website;
    @NotBlank(message = "Telephone is mandatory")
    private String telephone;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private String skypeId;
    private String country;
    private String timezone;
    private String currency;
    private String payType;
    private String dateTimeFormat;
    private String displayName;
}
