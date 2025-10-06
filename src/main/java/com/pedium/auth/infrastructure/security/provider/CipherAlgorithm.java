package com.pedium.auth.infrastructure.security.provider;

public interface CipherAlgorithm {
 
    public String hash(String rawPassword);

    public boolean verify(String password, String hashedPassword);
}