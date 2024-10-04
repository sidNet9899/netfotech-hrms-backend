package com.netfotech.auth.dto;

import com.netfotech.auth.entity.Role;

import lombok.Data;

@Data
public class AuthRequest {
	private String username;
	private String password;
	private Role role;
}
