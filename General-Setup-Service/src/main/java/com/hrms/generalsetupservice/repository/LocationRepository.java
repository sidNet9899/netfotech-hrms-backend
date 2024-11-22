package com.hrms.generalsetupservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.generalsetupservice.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
	 List<Location> findByCompanyId(Long companyId);

	Optional<Location> findByLocationCode(String locationCode);
}
