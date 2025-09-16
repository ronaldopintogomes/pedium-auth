package com.pedium.auth.infrastructure.repository;

import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.pedium.auth.domain.entity.User;
import com.pedium.auth.domain.gateway.UserGateway;

public class UserJdbcClientGateway implements UserGateway {

    private final JdbcClient JdbcClient;

    public UserJdbcClientGateway(JdbcClient jdbcClient) {
        this.JdbcClient = jdbcClient;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return JdbcClient.sql("")
            .param("username", username)
            .query(User.class)
            .optional();
    }
    
}