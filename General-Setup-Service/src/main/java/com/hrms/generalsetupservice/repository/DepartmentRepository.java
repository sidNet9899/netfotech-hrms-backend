package com.hrms.generalsetupservice.repository;
import com.hrms.generalsetupservice.entity.Department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	List<Department> findByCompanyId(Long companyId);
    Optional<Department> findByDepartmentNameAndCompanyId(String departmentName, Long companyId);

}
