package com.boot.taleboard_backend.controller;


import com.boot.taleboard_backend.entity.Role;
import com.boot.taleboard_backend.entity.User;
import com.boot.taleboard_backend.service.impl.CustomUserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CustomUserDetailsServiceImpl userService;

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Fetching all users...");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found!");
        } else {
            System.out.println("Found " + users.size() + " users.");
        }
        return users;
    }


    // Delete a user by ID
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Assign admin role to a user
    @PutMapping("/users/{id}/assign-admin")
    public void assignAdminRole(@PathVariable Long id) {
        userService.assignRole(id, Role.ADMIN);  // Pass Role enum
    }

    // Assign user role to a user
    @PutMapping("/users/{id}/assign-user")
    public void assignUserRole(@PathVariable Long id) {
        userService.assignRole(id, Role.USER);   // Pass Role enum
    }
}
