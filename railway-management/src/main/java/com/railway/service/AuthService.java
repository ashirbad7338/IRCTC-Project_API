package com.railway.service;

import com.railway.entity.Role;
import com.railway.entity.User;
import com.railway.repository.Repository;
import com.railway.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final Repository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(Repository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String loginOrRegister(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            // Try to authenticate the existing user
            try {
                // If authentication is successful, return JWT token
                return jwtUtil.generateToken(username);
            } catch (Exception e) {
                throw new RuntimeException("Invalid credentials!");
            }
        } else {
            // If user does not exist, register them as a normal user
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(Role.USER); // Default role is USER

            userRepository.save(newUser);
            return "User registered successfully! Please log in.";
        }
    }
}
