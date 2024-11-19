package com.netfotech.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netfotech.auth.dto.AuthRequest;
import com.netfotech.auth.entity.Role;
import com.netfotech.auth.entity.User;
import com.netfotech.auth.repository.UserRepository;
import com.netfotech.auth.security.JwtUtil;
import com.netfotech.auth.service.CustomUserDetailsService;
import com.netfotech.auth.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil; // Inject JwtUtil

	@Autowired
	private CustomUserDetailsService userDetailsService; // Inject CustomUserDetailsService

	// Register User (no Authorization required for the first registration -
	// SuperAdmin)
	@PostMapping("/register")
	public String registerUser(@RequestBody AuthRequest authRequest,
			@RequestHeader(value = "Authorization", required = false) String authToken) {

		// Check if this is the first registration (SuperAdmin creation)
		if (userRepository.count() == 0) {
			// Automatically set role to SUPER_ADMIN for the first registration
            authRequest.setRole(Role.SUPER_ADMIN);
		}else {
            // If users exist, validate authorization token for registering others
            if (authToken == null || !authToken.startsWith("Bearer ")) {
                return "Authorization token is required to register a user.";
            }

            // Extract and validate token
            String token = authToken.substring(7); // Remove 'Bearer ' prefix
            String username = jwtUtil.extractUsername(token);

            if (username == null || username.isEmpty()) {
                return "Invalid token.";
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails == null || !jwtUtil.validateToken(token, userDetails.getUsername())) {
                return "Invalid or expired token.";
            }

            // Get the user making the request
            User registeringUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Validate the role of the user making the registration request
            if (registeringUser.getRole() == Role.SUPER_ADMIN) {
                // SuperAdmin can register any role
                if (authRequest.getRole() == null) {
                    return "Role must be provided for user registration.";
                }
            } else if (registeringUser.getRole() == Role.ADMIN) {
                // Admin can only register MANAGER or EMPLOYEE roles
                if (authRequest.getRole() == null || 
                    (authRequest.getRole() != Role.MANAGER && authRequest.getRole() != Role.EMPLOYEE)) {
                    return "Admin can only register Manager or Employees.";
                }
            } else {
                return "Only SuperAdmin and Admin can register new users.";
            }
        }

        // Delegate registration to UserService
        userService.registerUser(authRequest);

        return "User registered successfully with role: " + authRequest.getRole();
    }

}