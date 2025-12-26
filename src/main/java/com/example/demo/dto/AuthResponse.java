package com.example.demo.dto;

public class AuthResponse {
    private String token;

    // Parameterized constructor - fixes the compilation error
    public AuthResponse(String token) {
        this.token = token;
    }

    // No-args constructor - recommended for JSON serialization
    public AuthResponse() {
    }

    // Getters and Setters
    public String getToken() { 
        return token; 
    }

    public void setToken(String token) { 
        this.token = token; 
    }
}