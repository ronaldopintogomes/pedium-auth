package com.pedium.auth.infrastructure.security;

import com.pedium.auth.core.application.security.CipherAlgorithmType;
import com.pedium.auth.infrastructure.security.provider.BCryptSecurityProvider;
import com.pedium.auth.infrastructure.security.provider.CipherAlgorithm;
import com.pedium.auth.infrastructure.security.provider.SHA256SecurityProvider;

public class CipherAlgorithmFactory {
 
    public static CipherAlgorithm getInstance(CipherAlgorithmType type) {
        switch(type) {
            case BCRYPT -> {
                return new BCryptSecurityProvider();
            }
            case SHA256 -> {
                return new SHA256SecurityProvider();
            }
            default -> throw new IllegalArgumentException("Algorithm not found: "+type);
        }
    }
}