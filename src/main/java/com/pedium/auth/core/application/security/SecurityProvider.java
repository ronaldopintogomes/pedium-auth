package com.pedium.auth.core.application.security;

public interface SecurityProvider {
    
    public String generateHash(String input, CipherAlgorithmType cipherInstance);
    public boolean verifyHash(String input, String toCompare, CipherAlgorithmType cipherInstance);
}