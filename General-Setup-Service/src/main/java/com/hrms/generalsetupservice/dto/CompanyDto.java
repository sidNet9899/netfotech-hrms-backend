package com.hrms.generalsetupservice.dto;

import lombok.Data;

@Data
public class CompanyDto {
	private String companyName;
    private String companyCode;
    private String address;
    private String website;
    private String telephone;
    private String email;
    private String skypeId;
    private String country;
    private String timezone;
    private String currency;
    private String payType;
    private String dateTimeFormat;
    private String displayName;
}
