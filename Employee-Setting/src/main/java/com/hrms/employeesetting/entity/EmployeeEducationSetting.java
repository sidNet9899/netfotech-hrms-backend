package com.hrms.employeesetting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmployeeEducationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    private String standard;    // Standard (e.g., Graduation, Post Graduation)
    private String courseName;  // Course Name (e.g., Arts, B.Com)
    private String status;      // Status (e.g., Active, Inactive)
}
