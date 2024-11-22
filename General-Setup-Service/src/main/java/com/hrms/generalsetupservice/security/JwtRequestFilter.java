package com.hrms.generalsetupservice.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hrms.generalsetupservice.feign.AuthClient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private AuthClient authClient;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        String token = authHeader.substring(7);
	        Claims claims = Jwts.parser()
	            .setSigningKey("ffshfjrowjormgowrdastegr")
	            .parseClaimsJws(token)
	            .getBody();

	        Boolean isValid = authClient.validateToken(authHeader);
	        if (isValid == null || !isValid) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
	            return;
	        }
	     // Extract roles
	        List<String> roles = claims.get("roles", List.class);
	        List<SimpleGrantedAuthority> authorities = roles.stream()
	            .map(SimpleGrantedAuthority::new)
	            .toList();

	        // Set authentication in SecurityContext
	        UsernamePasswordAuthenticationToken authentication =
	            new UsernamePasswordAuthenticationToken(null, null, authorities);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    } else {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid");
	        return;
	    }
	    chain.doFilter(request, response);
	}


}