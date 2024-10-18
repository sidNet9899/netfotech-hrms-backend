package com.netfotech.settingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Company name is required")
	private String companyName;
	
	@NotBlank(message = "Company code is required")
	private String companyCode;
	
	private String taxId;
	private String zipCode;
    private String address1;
    private String telephone;
    
    @Email(message = "Invalid email")
    private String email;
    
    private String skypeId;
    private String website;
    private String country;
    private String timeZone;
    private String currency;
    private String payType;
    private String dateTimeFormat;
    private String numberFormat;
    private String displayName;
	
    @Enumerated(EnumType.STRING)
    private Status status;
}
