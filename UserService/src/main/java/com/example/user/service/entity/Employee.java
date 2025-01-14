package com.example.user.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during JSON deserialization
@JsonInclude(JsonInclude.Include.ALWAYS)   // Include all fields, even if null
@JsonDeserialize
public class Employee {
	
	@JsonProperty("employeeId")
	private String employeeId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("employeeCode")
	private String employeeCode;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("department")
	private String department;

	@JsonProperty("officialEmail")
	private String officialEmail;


//    @JsonProperty("employeeId")
//    private String employeeId; // Primary key, auto-generated
//
//    @JsonProperty("userId")
//    private String userId;
//
//    @JsonProperty("employeeCode")
//    private String employeeCode; // Must be unique and non-nullable
//
//    @JsonProperty("salutation")
//    private String salutation; // Nullable
//
//    @JsonProperty("firstName")
//    private String firstName; // Non-nullable
//
//    @JsonProperty("middleName")
//    private String middleName; // Nullable
//
//    @JsonProperty("lastName")
//    private String lastName; // Non-nullable
//
//    @JsonProperty("company")
//    private String company; // Non-nullable
//
//    @JsonProperty("location")
//    private String location; // Non-nullable
//
//    @JsonProperty("department")
//    private String department; // Non-nullable
//
//    @JsonProperty("jobTitle")
//    private String jobTitle; // Non-nullable
//
//    @JsonProperty("dateOfBirth")
//    private LocalDate dateOfBirth; // Nullable
//
//    @JsonProperty("officialEmail")
//    private String officialEmail; // Non-nullable
//
//    @JsonProperty("dateOfJoining")
//    private LocalDate dateOfJoining; // Nullable
//
//    @JsonProperty("mobileNumber")
//    private String mobileNumber; // Non-nullable
//
//    @JsonProperty("role")
//    private String role; // Nullable
//
//    @JsonProperty("gender")
//    private String gender; // Nullable
//
//    @JsonProperty("maritalStatus")
//    private String maritalStatus; // Nullable
//
//    @JsonProperty("identificationType")
//    private String identificationType; // Nullable
//
//    @JsonProperty("uniqueIdentificationCode")
//    private String uniqueIdentificationCode; // Nullable
//
//    @JsonProperty("typeOfEmployment")
//    private String typeOfEmployment; // Nullable
//
//    @JsonProperty("reportingManager")
//    private String reportingManager; // Nullable
//
//    // Additional fields
//    @JsonProperty("password")
//    private String password;
//
//    @JsonProperty("employmentSource")
//    private String employmentSource;
//
//    @JsonProperty("status")
//    private String status;
//
//    @JsonProperty("nationality")
//    private String nationality;
//
//    @JsonProperty("placeOfBirth")
//    private String placeOfBirth;
//
//    @JsonProperty("bloodGroup")
//    private String bloodGroup;
//
//    // Emergency contact
//    @JsonProperty("emergencyFirstName")
//    private String emergencyFirstName;
//
//    @JsonProperty("emergencyLastName")
//    private String emergencyLastName;
//
//    @JsonProperty("emergencyAddress")
//    private String emergencyAddress;
//
//    @JsonProperty("emergencyMobileNumber")
//    private String emergencyMobileNumber;
//
//    @JsonProperty("emergencyCountryName")
//    private String emergencyCountryName;
//
//    // Address information
//    @JsonProperty("addressType")
//    private String addressType;
//
//    @JsonProperty("address")
//    private String address;
//
//    @JsonProperty("city")
//    private String city;
//
//    @JsonProperty("state")
//    private String state;
//
//    @JsonProperty("zipCode")
//    private String zipCode;
//
//    @JsonProperty("country")
//    private String country;
//
//    // Dependents
//    @JsonProperty("dependentName")
//    private String dependentName;
//
//    @JsonProperty("dependentAge")
//    private String dependentAge;
//
//    @JsonProperty("dependentMobileNo")
//    private String dependentMobileNo;
//
//    @JsonProperty("dependentEmailId")
//    private String dependentEmailId;
//
//    @JsonProperty("dependentRelation")
//    private String dependentRelation;
}
