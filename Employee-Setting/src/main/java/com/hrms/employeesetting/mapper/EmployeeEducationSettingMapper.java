package com.hrms.employeesetting.mapper;

import com.hrms.employeesetting.dto.EmployeeEducationSettingDTO;
import com.hrms.employeesetting.entity.EmployeeEducationSetting;

public class EmployeeEducationSettingMapper {

    // Convert Entity to DTO
    public static EmployeeEducationSettingDTO toDTO(EmployeeEducationSetting entity) {
        if (entity == null) return null;

        EmployeeEducationSettingDTO dto = new EmployeeEducationSettingDTO();
        dto.setId(entity.getId());
        dto.setStandard(entity.getStandard());
        dto.setCourseName(entity.getCourseName());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    // Convert DTO to Entity
    public static EmployeeEducationSetting toEntity(EmployeeEducationSettingDTO dto) {
        if (dto == null) return null;

        EmployeeEducationSetting entity = new EmployeeEducationSetting();
        entity.setId(dto.getId());
        entity.setStandard(dto.getStandard());
        entity.setCourseName(dto.getCourseName());
        entity.setStatus(dto.getStatus());

        return entity;
    }
}