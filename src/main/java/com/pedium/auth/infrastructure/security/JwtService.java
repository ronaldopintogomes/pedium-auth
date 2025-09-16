package com.pedium.auth.infrastructure.security;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import com.pedium.auth.domain.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService {
 
    private final String secret = "";

    public String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.getName())
            .setIssuedAt(new Date())
            .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
            .signWith(signingKey())
            .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(signingKey())
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    private Key signingKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}