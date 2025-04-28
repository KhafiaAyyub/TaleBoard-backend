package com.boot.taleboard_backend.service.impl;


import com.boot.taleboard_backend.entity.Role;
import com.boot.taleboard_backend.entity.User;
import com.boot.taleboard_backend.repository.UserRepository;
import com.boot.taleboard_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

//	List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

	 private final SecurityConfig securityConfig;

	    // Constructor Injection
	    @Autowired
	    public CustomUserDetailsServiceImpl(SecurityConfig securityConfig) {
	        this.securityConfig = securityConfig;
	    }
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    // ✅ Constructor Injection (recommended)
	    public CustomUserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user from the database using the email
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convert the user's role to a list of authorities
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        // Return the user with their roles as authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public void assignRole(Long userId, Role userRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Directly set the role
        user.setRole(userRole);

        userRepository.save(user);
    }



	public void deleteUser(Long id) {
        userRepository.deleteById(id);
		
	}

	public List<User> getAllUsers() {
        return userRepository.findAll();
	}

	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User createUser(String username, String email, String password, Role role) {
	    User user = new User();
	    user.setUsername(username);
	    user.setEmail(email);
	    user.setPassword(passwordEncoder.encode(password)); // Encrypt password
	    user.setRole(role); // Set the role!

	    return userRepository.save(user);
	}

	


}
