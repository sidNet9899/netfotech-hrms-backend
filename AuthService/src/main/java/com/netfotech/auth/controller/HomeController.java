package com.netfotech.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public String welcome() {
        return "Welcome to the Auth Service!";
    }

    @GetMapping("/secure")
    public String secureArea() {
        // Get the current authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Retrieve the username from the authentication object
        String username = authentication.getName();

        // Return a personalized welcome message
        return "Welcome, " + username + "! This is a protected area. You are authenticated!";
    }
}
