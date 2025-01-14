package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String employeeId; // Primary key, auto-generated
    private String userId;

    @Column(unique = true, nullable = false)
    private String employeeCode; // Must be unique and non-nullable

    private String salutation; // Nullable
    @Column(nullable = false)
    private String firstName; // Non-nullable
    private String middleName; // Nullable
    @Column(nullable = false)
    private String lastName; // Non-nullable

    @Column(nullable = false)
    private String company; // Non-nullable
    @Column(nullable = false)
    private String location; // Non-nullable
    @Column(nullable = false)
    private String department; // Non-nullable
    @Column(nullable = false)
    private String jobTitle; // Non-nullable

    private LocalDate dateOfBirth; // Nullable
    @Column(nullable = false)
    private String officialEmail; // Non-nullable
    private LocalDate dateOfJoining; // Nullable
    @Column(nullable = false)
    private String mobileNumber; // Non-nullable

    private String role; // Nullable
    private String gender; // Nullable
    private String maritalStatus; // Nullable
    private String identificationType; // Nullable
    private String uniqueIdentificationCode; // Nullable
    private String typeOfEmployment; // Nullable
    private String reportingManager; // Nullable

    // Additional fields (Optional for updates)
    private String password;
    private String employmentSource;
    private String status;
    private String nationality;
    private String placeOfBirth;
    private String bloodGroup;

    // Emergency contact (optional)
    private String emergencyFirstName;
    private String emergencyLastName;
    private String emergencyAddress;
    private String emergencyMobileNumber;
    private String emergencyCountryName;

    // Address information (optional)
    private String addressType;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    // Dependents (optional)
    private String dependentName;
    private String dependentAge;
    private String dependentMobileNo;
    private String dependentEmailId;
    private String dependentRelation;
}
