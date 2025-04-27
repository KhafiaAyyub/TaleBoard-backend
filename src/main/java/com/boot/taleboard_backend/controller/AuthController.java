package com.boot.taleboard_backend.controller;

import com.boot.taleboard_backend.dto.LoginRequest;
import com.boot.taleboard_backend.dto.RegisterRequest;
import com.boot.taleboard_backend.dto.AuthResponse;
import com.boot.taleboard_backend.entity.User;
import com.boot.taleboard_backend.service.CustomUserDetailsService;
import com.boot.taleboard_backend.service.impl.CustomUserDetailsServiceImpl;
import com.boot.taleboard_backend.repository.UserRepository;
import com.boot.taleboard_backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Inject BCryptPasswordEncoder

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Check if user already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken!");
        }

        // Create and save the new user
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));  // Hash the password

        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    // Login and generate JWT token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        // If authentication is successful, generate a JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token)); // Return JWT token
    }
}
