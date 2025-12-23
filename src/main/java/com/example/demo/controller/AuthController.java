package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.*;

import java.util.Map;

public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> register(RegisterRequest r) {
        User user = User.builder()
                .name(r.getName())
                .email(r.getEmail())
                .password(r.getPassword())
                .role(r.getRole())
                .build();
        return ResponseEntity.ok(userService.register(user));
    }

    public ResponseEntity<?> login(AuthRequest req) {
        User user = userService.findByEmail(req.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(
                Map.of("userId", user.getId(), "email", user.getEmail(), "role", user.getRole()),
                user.getEmail()
        );
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
    }
}
