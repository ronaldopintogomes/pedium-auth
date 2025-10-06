package com.pedium.auth.core.application.gateway;

import java.util.Optional;

import com.pedium.auth.core.domain.entity.User;

public interface DatabaseGateway {
 
    public Optional<User> findByUsername(String username);
}