package com.boot.taleboard_backend.repository;


import com.boot.taleboard_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // For login & registration checks


    // Method to check if a user exists by email
    boolean existsByEmail(String email);

}
