package com.boot.taleboard_backend.controller;

import com.boot.taleboard_backend.entity.User;
import com.boot.taleboard_backend.service.impl.CustomUserDetailsServiceImpl;
import com.boot.taleboard_backend.dto.RegisterRequest;
import com.boot.taleboard_backend.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private CustomUserDetailsServiceImpl userService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest request) {
        // Set the default role to USER if not provided in the request
        Role role = request.getRole() != null ? Role.valueOf(request.getRole()) : Role.USER;
        User user = userService.createUser(request.getUsername(), request.getEmail(), request.getPassword(), role);
        return ResponseEntity.ok(user);
    }

    // Endpoint for getting user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Endpoint for updating user roles (admin can update role)
    @PutMapping("/{id}/assign-role")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @RequestParam String role) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase());  // Convert the string to Role enum
            userService.assignRole(id, userRole);
            return ResponseEntity.ok("Role assigned to user");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role provided");
        }
    }

    // Endpoint for deleting a user (admin can delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}