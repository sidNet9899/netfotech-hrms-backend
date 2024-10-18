package com.netfotech.settingservice.dto;

import com.netfotech.settingservice.entity.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyDto {
	
	@NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company code is required")
    @Size(min = 3, max = 10, message = "Company code should be between 3 and 10 characters")
    private String companyCode;

    @Email(message = "Email should be valid")
    private String email;
    
    private String taxId;
    private String zipCode;
    private String address1;
    private String telephone;
    private String skypeId;
    private String website;
    private String country;
    private String timeZone;
    private String currency;
    private String payType;
    private String dateTimeFormat;
    private String numberFormat;
    private String displayName;
    
    private Status status;
}
