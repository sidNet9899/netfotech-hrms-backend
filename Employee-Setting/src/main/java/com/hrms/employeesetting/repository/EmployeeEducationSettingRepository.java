package com.hrms.employeesetting.repository;

import com.hrms.employeesetting.entity.EmployeeEducationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEducationSettingRepository extends JpaRepository<EmployeeEducationSetting, Long> {
    // Additional query methods can be added here if required
}