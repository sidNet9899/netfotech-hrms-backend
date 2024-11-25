package com.hrms.employeesetting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.employeesetting.entity.RequestReason;
import com.hrms.employeesetting.service.RequestReasonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/request-reasons")
@RequiredArgsConstructor
public class RequestReasonController {

    private final RequestReasonService requestReasonService;

    @GetMapping
    public ResponseEntity<List<RequestReason>> getAllRequestReasons() {
        List<RequestReason> reasons = requestReasonService.getAllRequestReasons();
        return ResponseEntity.ok(reasons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestReason> getRequestReasonById(@PathVariable Long id) {
        RequestReason reason = requestReasonService.getRequestReasonById(id);
        if (reason != null) {
            return ResponseEntity.ok(reason);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<RequestReason> createRequestReason(@RequestBody RequestReason requestReason) {
        RequestReason createdReason = requestReasonService.createRequestReason(requestReason);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReason);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestReason> updateRequestReason(
            @PathVariable Long id,
            @RequestBody RequestReason updatedRequestReason) {
        RequestReason updatedReason = requestReasonService.updateRequestReason(id, updatedRequestReason);
        if (updatedReason != null) {
            return ResponseEntity.ok(updatedReason);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequestReason(@PathVariable Long id) {
        if (requestReasonService.getRequestReasonById(id) != null) {
            requestReasonService.deleteRequestReason(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
