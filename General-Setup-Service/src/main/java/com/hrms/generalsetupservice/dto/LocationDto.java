package com.hrms.generalsetupservice.dto;

import lombok.Data;

@Data
public class LocationDto {
	
	private Long id;
    private Long companyId;
    private String companyName; // For readability in the response
    private String locationName;
    private String locationCode;
    private String state;
    private String city;
    private String timeZone;
    private Double latitude;
    private Double longitude;
    private Double radius;
    private String status;

}
