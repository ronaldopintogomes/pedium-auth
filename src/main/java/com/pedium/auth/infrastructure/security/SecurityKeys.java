package com.pedium.auth.infrastructure.security;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.pedium.auth.infrastructure.security.jwt.JwtProperties;

@Configuration
public class SecurityKeys {

    private final JwtProperties jwtProperties;

    public SecurityKeys(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
    
    @Bean
    public RSAPrivateKey rsaPrivateKey() throws Exception {
        Resource privateKey = jwtProperties.getPrivateKey();
        try(var in = privateKey.getInputStream()) {
            return (RSAPrivateKey)KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(in.readAllBytes()));
        }
    }

    @Bean
    public RSAPublicKey rsaPublicKey() throws Exception {
        Resource publicKey = jwtProperties.getPublicKey();
        try(var in = publicKey.getInputStream()) {
            return (RSAPublicKey)KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(in.readAllBytes()));
        }
    }
}