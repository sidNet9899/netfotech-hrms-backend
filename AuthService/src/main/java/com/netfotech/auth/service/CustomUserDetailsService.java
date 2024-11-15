package com.netfotech.auth.service;
import com.netfotech.auth.entity.User;
import com.netfotech.auth.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRole() != null ? List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())) : List.of()
                		);
    }
}

//// Use UserBuilder to construct UserDetails with roles
//UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
//builder.password(user.getPassword());
//
//// Assign the role to the user. Spring Security requires ROLE_ prefix.
//builder.roles(user.getRole().name());
//
//return builder.build();