package com.hrms.employeesetting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.employeesetting.entity.RequestReason;

@Repository
public interface RequestReasonRepository extends JpaRepository<RequestReason, Long> {
    List<RequestReason> findByStatus(RequestReason.Status status);
}

