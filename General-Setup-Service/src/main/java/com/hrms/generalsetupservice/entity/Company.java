package com.hrms.generalsetupservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
