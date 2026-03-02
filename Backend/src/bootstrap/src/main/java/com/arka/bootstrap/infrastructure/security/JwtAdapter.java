package com.arka.bootstrap.infrastructure.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.arka.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAdapter {

    private final JwtProperties jwtProperties;  
  
    private SecretKey getKey() {  
        return Keys.hmacShaKeyFor(  
            Decoders.BASE64.decode(jwtProperties.getSecret())  
        );  
    }

    public String generateToken(String username, String role, Long userId) {  
        return Jwts.builder()  
            .subject(username)  
            .claim("role", role) 
            .claim("userId", userId) 
            .issuedAt(new Date())  
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))  
            .signWith(getKey())  
            .compact();  
    }  
  
    public Claims extractClaims(String token) {  
        return Jwts.parser()  
            .verifyWith(getKey())  
            .build()  
            .parseSignedClaims(token)  
            .getPayload();  
    }  
  
    public String extractUsername(String token) {  
        return extractClaims(token).getSubject();  
    }  
  
    public String extractRole(String token) {  
        return extractClaims(token).get("role", String.class);  
    }  

    public Long extractUserId(String token) {  
        return extractClaims(token).get("userId", Long.class);  
    }  
  
    public boolean isTokenValid(String token) {  
        try {  
            extractClaims(token);  
            return true;  
        } catch (JwtException | IllegalArgumentException e) {  
            return false;  
        }  
    }
    
}
