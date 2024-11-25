package com.netfotech.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netfotech.auth.dto.AuthRequest;
import com.netfotech.auth.dto.AuthResponse;
import com.netfotech.auth.security.JwtUtil;
import com.netfotech.auth.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;


	
	@PostMapping("/login")
	public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
	    authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

	    final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
	    
	    // Retrieve roles from UserDetails
	    List<String> roles = userDetails.getAuthorities().stream()
	            .map(grantedAuthority -> grantedAuthority.getAuthority())
	            .toList(); // Convert to List<String>

	    // Pass the username and roles to generateToken
	    final String jwt = jwtUtil.generateToken(userDetails.getUsername(), roles);

	    return new AuthResponse(jwt);
	}
	
	@GetMapping("/validate")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
	    String username = null;
	    if (token != null && token.startsWith("Bearer ")) {
	        token = token.substring(7); // Remove "Bearer " prefix
	        username = jwtUtil.extractUsername(token);
	    }
	    
	    boolean isValid = jwtUtil.validateToken(token, username);
	    return ResponseEntity.ok(isValid);
	}

}

//// Only SuperAdmin can access this endpoint
//@GetMapping("/admin")
//@PreAuthorize("hasRole('SuperAdmin')")
//public String superAdminArea() {
//	return "You are accessing a SuperAdmin-only area!";
//}
//
//// Admin and SuperAdmin can access this
//@GetMapping("/admin-only")
//@PreAuthorize("hasAnyRole('Admin', 'SuperAdmin')")
//public String adminArea() {
//	return "Welcome, Admin!";
//}


//@PostMapping("/login")
//public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
//	authenticationManager.authenticate(
//			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//
//	final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
//	final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//	return new AuthResponse(jwt); // Now uses the constructor correctly
//}