package com.pedium.auth.infrastructure.security.provider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SHA256SecurityProvider implements CipherAlgorithm {

    private final String salt = "auth"; 

    @Override
    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedInput = salt + input;
            byte[] hashBytes = digest.digest(saltedInput.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("ERROR GENERATING HASH", e);
        }
    }

    @Override
    public boolean verify(String input, String hashStored) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] computedHash = digest.digest((salt + input).getBytes(StandardCharsets.UTF_8));
            byte[] storedHashBytes = HexFormat.of().parseHex(hashStored);
            return MessageDigest.isEqual(computedHash, storedHashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}