package com.pedium.auth.infrastructure.repository.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

public class SqlLoader {
    public static String load(String query) {
            try {
                var resource = new ClassPathResource("/sql/"+query);
                return Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Error loading SQL: "+e);
            }
        }   
}