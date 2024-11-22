package com.hrms.generalsetupservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.generalsetupservice.dto.LocationDto;
import com.hrms.generalsetupservice.entity.Company;
import com.hrms.generalsetupservice.entity.Location;
import com.hrms.generalsetupservice.entity.Status;
import com.hrms.generalsetupservice.exception.ResourceAlreadyExistsException;
import com.hrms.generalsetupservice.exception.ResourceNotFoundException;
import com.hrms.generalsetupservice.repository.CompanyRepository;
import com.hrms.generalsetupservice.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
    private LocationRepository locationRepository;
	
	@Autowired
    private CompanyRepository companyRepository;
	
	// Create a new location
    public LocationDto createLocation(LocationDto locationDTO) {
        // Check if a location with the same location code already exists
        Optional<Location> existingLocation = locationRepository.findByLocationCode(locationDTO.getLocationCode());
        if (existingLocation.isPresent()) {
            throw new ResourceAlreadyExistsException("Location already exists with code: " + locationDTO.getLocationCode());
        }

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
        location.setStatus(Status.valueOf(locationDTO.getStatus()));

        Location savedLocation = locationRepository.save(location);

        locationDTO.setId(savedLocation.getId());
        locationDTO.setCompanyName(company.getCompanyName());
        return locationDTO;
    }
	
    // Get location by ID
    public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with ID: " + id));
        return mapToDTO(location);
    }
    // Get locations by company ID
	public List<LocationDto> getLocationsByCompanyId(Long companyId) {
        return locationRepository.findByCompanyId(companyId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
	
	// Update an existing location
    public LocationDto updateLocation(Long id, LocationDto locationDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with ID: " + id));

        // Update only the provided fields
        if (locationDTO.getLocationName() != null) location.setLocationName(locationDTO.getLocationName());
        if (locationDTO.getLocationCode() != null) location.setLocationCode(locationDTO.getLocationCode());
        if (locationDTO.getState() != null) location.setState(locationDTO.getState());
        if (locationDTO.getCity() != null) location.setCity(locationDTO.getCity());
        if (locationDTO.getTimeZone() != null) location.setTimeZone(locationDTO.getTimeZone());
        if (locationDTO.getLatitude() != null) location.setLatitude(locationDTO.getLatitude());
        if (locationDTO.getLongitude() != null) location.setLongitude(locationDTO.getLongitude());
        if (locationDTO.getRadius() != null) location.setRadius(locationDTO.getRadius());
        if (locationDTO.getStatus() != null) location.setStatus(Status.valueOf(locationDTO.getStatus()));

        Location updatedLocation = locationRepository.save(location);
        return mapToDTO(updatedLocation);
    }
	
    //Delete a location
	public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with ID: " + id));
        locationRepository.delete(location);
    }
	
	// Map Location entity to LocationDto
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
        dto.setStatus(location.getStatus().toString());
        return dto;
    }

}
