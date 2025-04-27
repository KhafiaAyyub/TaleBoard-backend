package com.boot.taleboard_backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    // No additional methods are required for now, as `UserDetailsService` already has `loadUserByUsername`
}
