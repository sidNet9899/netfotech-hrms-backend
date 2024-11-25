package com.hrms.employeesetting.repository;

import com.hrms.employeesetting.entity.EducationStandard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationStandardRepository extends JpaRepository<EducationStandard, Long> {
    // JpaRepository already provides common methods like findById, existsById, deleteById, etc.
}
