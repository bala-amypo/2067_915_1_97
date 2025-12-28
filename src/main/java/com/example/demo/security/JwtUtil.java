package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMillis;

    /* ================= DEFAULT CONSTRUCTOR ================= */
    public JwtUtil() {
        this(
            "ThisIsASecretKeyForJwtThatIsAtLeastThirtyTwoCharactersLong",
            1000 * 60 * 60
        );
    }

    /* ================= CONSTRUCTOR USED BY SECURITY CONFIG ================= */
    public JwtUtil(String secret, int expirationMillis) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMillis = expirationMillis;
    }

    /* ================= GENERATE TOKEN (CLAIMS + SUBJECT) ================= */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Dat
