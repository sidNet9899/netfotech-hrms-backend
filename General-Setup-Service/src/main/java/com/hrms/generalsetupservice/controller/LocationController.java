package com.hrms.generalsetupservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.generalsetupservice.dto.LocationDto;
import com.hrms.generalsetupservice.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
	
	@Autowired
    private LocationService locationService;

    @PostMapping("/location")
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto LocationDto) {
        LocationDto createdLocation = locationService.createLocation(LocationDto);
        return ResponseEntity.ok(createdLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long id) {
        LocationDto location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<LocationDto>> getLocationsByCompanyId(@PathVariable Long companyId) {
        List<LocationDto> locations = locationService.getLocationsByCompanyId(companyId);
        return ResponseEntity.ok(locations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Long id, @RequestBody LocationDto LocationDto) {
        LocationDto updatedLocation = locationService.updateLocation(id, LocationDto);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Location deleted successfully.");
    }

}
