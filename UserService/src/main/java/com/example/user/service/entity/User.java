package com.example.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.user.service.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
    private String userId ;
	@Column( unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient // This field will not be stored in the database
    private List<Employee> employeeManagementData = new ArrayList<>();
    
//	@Transient
//	private List<Attenadance> attendance = new ArrayList<>();



}


//@Id
//@Column(name= "ID")
//private String userId;
//@Column(name= "Name")
//private String name;
//@Column(name= "Email")
//private String email;
//@Column(name= "About")
//private String about;

//@Transient
//private List<Rating> ratings = new ArrayList<>(); // not store in database


//@Transient
//private List<Hotel> hotels = new ArrayList<>();
