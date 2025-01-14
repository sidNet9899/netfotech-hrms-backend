package com.example.employeemanagement.impl;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
    //	String randomEmployeeId = UUID.randomUUID().toString();
	//	user.setUserId(randomUserId);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(String employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        // Update all fields dynamically
        existingEmployee.setEmployeeCode(updatedEmployee.getEmployeeCode());
        existingEmployee.setSalutation(updatedEmployee.getSalutation());
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setMiddleName(updatedEmployee.getMiddleName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setCompany(updatedEmployee.getCompany());
        existingEmployee.setLocation(updatedEmployee.getLocation());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setJobTitle(updatedEmployee.getJobTitle());
        existingEmployee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        existingEmployee.setOfficialEmail(updatedEmployee.getOfficialEmail());
        existingEmployee.setDateOfJoining(updatedEmployee.getDateOfJoining());
        existingEmployee.setMobileNumber(updatedEmployee.getMobileNumber());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setGender(updatedEmployee.getGender());
        existingEmployee.setMaritalStatus(updatedEmployee.getMaritalStatus());
        existingEmployee.setIdentificationType(updatedEmployee.getIdentificationType());
        existingEmployee.setUniqueIdentificationCode(updatedEmployee.getUniqueIdentificationCode());
        existingEmployee.setTypeOfEmployment(updatedEmployee.getTypeOfEmployment());
        existingEmployee.setReportingManager(updatedEmployee.getReportingManager());
        existingEmployee.setPassword(updatedEmployee.getPassword());
        existingEmployee.setEmploymentSource(updatedEmployee.getEmploymentSource());
        existingEmployee.setStatus(updatedEmployee.getStatus());
        existingEmployee.setNationality(updatedEmployee.getNationality());
        existingEmployee.setPlaceOfBirth(updatedEmployee.getPlaceOfBirth());
        existingEmployee.setBloodGroup(updatedEmployee.getBloodGroup());
        existingEmployee.setEmergencyFirstName(updatedEmployee.getEmergencyFirstName());
        existingEmployee.setEmergencyLastName(updatedEmployee.getEmergencyLastName());
        existingEmployee.setEmergencyAddress(updatedEmployee.getEmergencyAddress());
        existingEmployee.setEmergencyMobileNumber(updatedEmployee.getEmergencyMobileNumber());
        existingEmployee.setEmergencyCountryName(updatedEmployee.getEmergencyCountryName());
        existingEmployee.setAddressType(updatedEmployee.getAddressType());
        existingEmployee.setAddress(updatedEmployee.getAddress());
        existingEmployee.setCity(updatedEmployee.getCity());
        existingEmployee.setState(updatedEmployee.getState());
        existingEmployee.setZipCode(updatedEmployee.getZipCode());
        existingEmployee.setCountry(updatedEmployee.getCountry());
        existingEmployee.setDependentName(updatedEmployee.getDependentName());
        existingEmployee.setDependentAge(updatedEmployee.getDependentAge());
        existingEmployee.setDependentMobileNo(updatedEmployee.getDependentMobileNo());
        existingEmployee.setDependentEmailId(updatedEmployee.getDependentEmailId());
        existingEmployee.setDependentRelation(updatedEmployee.getDependentRelation());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }
    
    
    @Override
    public List<Employee> getEmployeesByUserId(String userId) {
        return employeeRepository.findByUserId(userId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
