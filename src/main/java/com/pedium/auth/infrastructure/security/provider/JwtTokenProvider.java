package com.pedium.auth.infrastructure.security.provider;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.pedium.auth.core.application.security.TokenProvider;
import com.pedium.auth.core.domain.entity.Role;
import com.pedium.auth.core.domain.entity.User;
import com.pedium.auth.infrastructure.security.jwt.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final RSAPrivateKey rsaPrivateKey;
    private final RSAPublicKey rsaPublicKey;
    private final JwtProperties jwtProperties;
    private String token;

    public JwtTokenProvider(RSAPrivateKey rsaPrivateKey, RSAPublicKey rsaPublicKey, JwtProperties jwtProperties) {
        this.rsaPrivateKey = rsaPrivateKey;
        this.rsaPublicKey = rsaPublicKey;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.getUid())
            .claim("name", user.getName())
            .claim("contact", Map.of(
                "email", user.getContact().getEmail(),
                "cellphone", user.getContact().getCellphone()))
            .claim("roles", user.getRoles().stream().map(Role::name).collect(Collectors.toList()))
            .setIssuedAt(new Date())
            .setExpiration(Date.from(Instant.now().plusSeconds(jwtProperties.getDateExpiration())))
            .signWith(rsaPrivateKey)
            .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        if(token == null || token.isEmpty()) {
            return false;
        } 
        try {
            Jwts.parserBuilder()
            .setSigningKey(rsaPublicKey)
            .build()
            .parseClaimsJws(token);
            return true;
        } catch(JwtException e) {
            return false;
        } 
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Claims getClaims() {
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
            .setSigningKey(rsaPublicKey)
            .build()
            .parseClaimsJws(token);
        return jwsClaims.getBody();
    }

    public String getClaimNameFromToken() {
        return getClaims().get("name", String.class);
    }

    public List<GrantedAuthority> getClaimRolesFromToken() {
        List<String> list = getClaims().get("roles", List.class);
        
        return list.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
            .collect(Collectors.toList());
    }
}