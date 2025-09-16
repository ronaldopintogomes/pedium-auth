package com.pedium.auth.domain.usecase.impl;

import com.pedium.auth.domain.exception.InvalidCredentialsException;
import com.pedium.auth.domain.exception.UserNotFoundException;
import com.pedium.auth.domain.gateway.UserGateway;
import com.pedium.auth.domain.usecase.LoginUseCase;
import com.pedium.auth.infrastructure.security.JwtService;

public class LoginUser implements LoginUseCase {

    private final UserGateway userGateway;
    private final JwtService jwtService;

    public LoginUser(UserGateway userGateway, JwtService jwtService) {
        this.userGateway = userGateway;
        this.jwtService = jwtService;
    }

    @Override
    public String login(String username, String password) throws UserNotFoundException, InvalidCredentialsException {
        var user = userGateway.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User "+username+" not found!"));
        if(!user.getPassword().equals(password)) {//usar enconder para comparar a senha recebida com a do banco
            throw new InvalidCredentialsException("Invalid password!");
        }
        return jwtService.generateToken(user);
    }
    
}