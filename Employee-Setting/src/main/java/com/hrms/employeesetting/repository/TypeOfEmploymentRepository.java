package com.hrms.employeesetting.repository;

import com.hrms.employeesetting.entity.TypeOfEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TypeOfEmploymentRepository extends JpaRepository<TypeOfEmployment, Long> {
    List<TypeOfEmployment> findByStatus(String status); // Optional: Find all active/inactive types
}