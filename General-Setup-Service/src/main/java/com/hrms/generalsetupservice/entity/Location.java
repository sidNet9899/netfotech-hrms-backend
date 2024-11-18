package com.hrms.generalsetupservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Location {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotBlank(message = "Location name cannot be blank")
    @Size(max = 100, message = "Location name must be under 100 characters")
    private String locationName;

    @NotBlank(message = "Location code cannot be blank")
    @Size(max = 20, message = "Location code must be under 20 characters")
    private String locationCode;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Time zone cannot be blank")
    private String timeZone;

    @NotNull(message = "Latitude cannot be null")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    private Double longitude;

    @NotNull(message = "Radius cannot be null")
    private Double radius;
    
    @NotNull
    private String status;
//    @Enumerated(EnumType.STRING)
//    @NotNull(message = "Status cannot be null")
//    private Status status;

}
