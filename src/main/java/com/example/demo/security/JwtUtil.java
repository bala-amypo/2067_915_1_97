package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email) {
        return "dummy-token-for-" + email;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy-token");
    }

    public String extractUsername(String token) {
        if (token == null) return null;
        return token.replace("dummy-token-for-", "");
    }
}
