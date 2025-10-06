package com.pedium.auth.infrastructure.security.provider;

import com.pedium.auth.core.application.security.CipherAlgorithmType;
import com.pedium.auth.core.application.security.SecurityProvider;
import com.pedium.auth.infrastructure.security.CipherAlgorithmFactory;

public class DefaultSecurityProvider implements SecurityProvider {

    @Override
    public String generateHash(String input, CipherAlgorithmType algorithmType) {
        CipherAlgorithm algorithm = CipherAlgorithmFactory.getInstance(algorithmType);
        return algorithm.hash(input);
    }

    @Override
    public boolean verifyHash(String input, String toCompare, CipherAlgorithmType algorithmType) {
        CipherAlgorithm algorithm = CipherAlgorithmFactory.getInstance(algorithmType);
        return algorithm.verify(input, toCompare);
    }
    
}