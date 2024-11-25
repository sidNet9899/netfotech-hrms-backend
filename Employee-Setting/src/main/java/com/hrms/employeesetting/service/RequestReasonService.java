package com.hrms.employeesetting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hrms.employeesetting.entity.RequestReason;
import com.hrms.employeesetting.repository.RequestReasonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestReasonService {

    private final RequestReasonRepository requestReasonRepository;

    public List<RequestReason> getAllRequestReasons() {
        return requestReasonRepository.findAll();
    }

    public RequestReason getRequestReasonById(Long id) {
        // Return null if not found instead of throwing an exception
        return requestReasonRepository.findById(id).orElse(null);
    }

    public RequestReason createRequestReason(RequestReason requestReason) {
        return requestReasonRepository.save(requestReason);
    }

    public RequestReason updateRequestReason(Long id, RequestReason updatedRequestReason) {
        // Find the existing resource
        Optional<RequestReason> existingOptional = requestReasonRepository.findById(id);
        if (existingOptional.isPresent()) {
            RequestReason existing = existingOptional.get();
            BeanUtils.copyProperties(updatedRequestReason, existing, "id", "createdAt");
            return requestReasonRepository.save(existing);
        }
        return null; // Handle gracefully if the resource is not found
    }

    public void deleteRequestReason(Long id) {
        // Check existence before deleting
        if (requestReasonRepository.existsById(id)) {
            requestReasonRepository.deleteById(id);
        }
    }
}
