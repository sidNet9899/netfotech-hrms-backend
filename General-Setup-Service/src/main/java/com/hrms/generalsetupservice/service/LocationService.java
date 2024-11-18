package com.hrms.generalsetupservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.generalsetupservice.dto.LocationDto;
import com.hrms.generalsetupservice.entity.Company;
import com.hrms.generalsetupservice.entity.Location;
import com.hrms.generalsetupservice.exception.ResourceNotFoundException;
import com.hrms.generalsetupservice.repository.CompanyRepository;
import com.hrms.generalsetupservice.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
    private LocationRepository locationRepository;
	
	@Autowired
    private CompanyRepository companyRepository;
	
	public LocationDto createLocation(LocationDto locationDTO) {
        Company company = companyRepository.findById(locationDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + locationDTO.getCompanyId()));

        Location location = new Location();
        location.setCompany(company);
        location.setLocationName(locationDTO.getLocationName());
        location.setLocationCode(locationDTO.getLocationCode());
        location.setState(locationDTO.getState());
        location.setCity(locationDTO.getCity());
        location.setTimeZone(locationDTO.getTimeZone());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setRadius(locationDTO.getRadius());
        location.setStatus(locationDTO.getStatus());
//        location.setStatus(Status.valueOf(locationDTO.getStatus()));

        Location savedLocation = locationRepository.save(location);

        locationDTO.setId(savedLocation.getId());
        locationDTO.setCompanyName(company.getCompanyName());
        return locationDTO;
    }
	
	public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with ID: " + id));
        return mapToDTO(location);
    }
	
	public List<LocationDto> getLocationsByCompanyId(Long companyId) {
        return locationRepository.findByCompanyId(companyId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
	
	public LocationDto updateLocation(Long id, LocationDto locationDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with ID: " + id));

        location.setLocationName(locationDTO.getLocationName());
        location.setLocationCode(locationDTO.getLocationCode());
        location.setState(locationDTO.getState());
        location.setCity(locationDTO.getCity());
        location.setTimeZone(locationDTO.getTimeZone());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setRadius(locationDTO.getRadius());
        location.setStatus(locationDTO.getStatus());
//        location.setStatus(Status.valueOf(locationDTO.getStatus()));

        Location updatedLocation = locationRepository.save(location);
        return mapToDTO(updatedLocation);
    }
	
	public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with ID: " + id));
        locationRepository.delete(location);
    }
	
	
	private LocationDto mapToDTO(Location location) {
        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setCompanyId(location.getCompany().getId());
        dto.setCompanyName(location.getCompany().getCompanyName());
        dto.setLocationName(location.getLocationName());
        dto.setLocationCode(location.getLocationCode());
        dto.setState(location.getState());
        dto.setCity(location.getCity());
        dto.setTimeZone(location.getTimeZone());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setRadius(location.getRadius());
        dto.setStatus(location.getStatus());
//        dto.setStatus(location.getStatus().toString());
        return dto;
    }

}
