package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Employee {
    @Id
    private String username; // Renamed from employeeCode to username

    private String password;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String personalEmail;

    private String location;
    private String age;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String middleName;
    private String employmentSource;
    private String mobileNumber;
    private String maritalStatus;
    private String placeOfBirth;
    private String uniqueIdentificationDocument;
    private String uniqueIdentificationCode;
    private String bloodGroup;
    private String typeOfEmployment;
    private String officialEmail;
    private String nationality;
    private String officePhone;
    private String homePhone;
    private String company;
    private String department;
    private String jobTitle;
    private String jobLevel;
    private String salutation;
    private String fatherName;

    // Emergency Contact
    private String emergencyFirstName;
    private String emergencyLastName;
    private String emergencyAddress;
    private String emergencyMobileNumber;
    private String emergencyCountryName;
    private String emergencyAlternateMobileNumber;
    private String emergencyEmailId;
    private String emergencyAlternateEmailId;

    // Address Information
    private String addressType;
    private String address;
    private String city;
    private String telephone;
    private String zipCode;
    private String country;
    private String state;

    // Dependents
    private String dependentName;
    private String dependentAge;
    private String dependentMobileNo;
    private String dependentEmailId;
    private String dependentRelation;
}
