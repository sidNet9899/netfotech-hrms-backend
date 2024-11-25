package com.hrms.employeesetting.dto;

import lombok.Data;

@Data
public class EmployeeEducationSettingDTO {
    private Long id;
    private String standard;
    private String courseName;
    private String status;
}