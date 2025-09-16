package com.pedium.auth.domain.gateway;

import java.util.Optional;

import com.pedium.auth.domain.entity.User;

public interface UserGateway {
    
    public Optional<User> findByUsername(String username);
}