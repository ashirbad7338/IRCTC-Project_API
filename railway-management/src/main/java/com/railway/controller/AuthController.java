package com.railway.controller;

import com.railway.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login-or-register")
    public Map<String, String> loginOrRegister(@RequestBody Map<String, String> request) {
        String response = authService.loginOrRegister(
                request.get("username"),
                request.get("password")
        );
        return Map.of("message", response);
   }
}
