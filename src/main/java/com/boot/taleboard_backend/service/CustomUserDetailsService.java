package com.boot.taleboard_backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.boot.taleboard_backend.entity.Role;
import com.boot.taleboard_backend.entity.User;

public interface CustomUserDetailsService extends UserDetailsService {
    // No additional methods are required for now, as `UserDetailsService` already has `loadUserByUsername`
	
	List<User> getAllUsers();
    void deleteUser(Long id);
    void assignRole(Long id, Role role);
	User createUser(String username, String email, String password, Role role);

	
	
	
	
}
