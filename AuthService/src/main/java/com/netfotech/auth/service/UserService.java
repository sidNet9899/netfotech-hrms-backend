package com.netfotech.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.netfotech.auth.dto.AuthRequest;
import com.netfotech.auth.entity.Role;
import com.netfotech.auth.entity.User;
import com.netfotech.auth.exception.UserAlreadyExistsException;
import com.netfotech.auth.exception.UserRegistrationException;
import com.netfotech.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	

	// General method to register a user based on role provided in the AuthRequest DTO
    public User registerUser(AuthRequest authRequest) {
        // Check if the username is already taken
        Optional<User> existingUser = userRepository.findByUsername(authRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Username is already taken.");
        }

        // Create a new user and set the properties
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        // Set the role from the AuthRequest
        Role role = authRequest.getRole();
        if (role == null) {
            throw new UserRegistrationException("Role is required for registration.");
        }
        user.setRole(role);

        try {
            // Save the new user
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserRegistrationException("Error registering the user. Please try again.", ex);
        } catch (Exception ex) {
            throw new UserRegistrationException("Unexpected error during registration.", ex);
        }
    }

}