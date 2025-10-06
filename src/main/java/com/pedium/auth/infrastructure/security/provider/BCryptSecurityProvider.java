package com.pedium.auth.infrastructure.security.provider;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptSecurityProvider implements CipherAlgorithm {

    @Override
    public String hash(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt());
    }

    @Override
    public boolean verify(String inputHashed, String hash) {
        return BCrypt.checkpw(inputHashed, hash);
    }
}