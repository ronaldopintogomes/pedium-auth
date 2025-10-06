package com.pedium.auth.infrastructure.repository.gateway;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.pedium.auth.core.application.gateway.DatabaseGateway;
import com.pedium.auth.core.domain.entity.User;
import com.pedium.auth.infrastructure.repository.util.SqlLoader;

@Primary
@Repository
public class UserJdbcClientGateway implements DatabaseGateway {

    private final JdbcClient JdbcClient;

    public UserJdbcClientGateway(JdbcClient jdbcClient) {
        this.JdbcClient = jdbcClient;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return JdbcClient.sql(SqlLoader.load("find_by_username.sql"))
            .param("username", username)
            .query(User.class)
            .optional();
    }
    
}