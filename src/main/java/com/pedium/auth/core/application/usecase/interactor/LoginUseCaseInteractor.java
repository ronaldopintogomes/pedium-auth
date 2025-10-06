package com.pedium.auth.core.application.usecase.interactor;

import com.pedium.auth.core.application.exception.InvalidCredentialsException;
import com.pedium.auth.core.application.exception.UserNotFoundException;
import com.pedium.auth.core.application.gateway.DatabaseGateway;
import com.pedium.auth.core.application.security.CipherAlgorithmType;
import com.pedium.auth.core.application.security.SecurityProvider;
import com.pedium.auth.core.application.security.TokenProvider;
import com.pedium.auth.core.application.usecase.LoginUseCase;

public class LoginUseCaseInteractor implements LoginUseCase {

    private final DatabaseGateway databaseGateway;
    private final TokenProvider tokenProvider;
    private final SecurityProvider securityProvider;

    public LoginUseCaseInteractor(DatabaseGateway databaseGateway, TokenProvider tokenProvider, SecurityProvider securityProvider) {
        this.databaseGateway = databaseGateway;
        this.tokenProvider = tokenProvider;
        this.securityProvider = securityProvider;
    }

    @Override
    public String login(String username, String password) throws UserNotFoundException, InvalidCredentialsException {
        var user = databaseGateway.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        if(!securityProvider.verifyHash(password, user.getPassword(), CipherAlgorithmType.SHA256)) {
            throw new InvalidCredentialsException("Invalid password!");
        }
        return tokenProvider.generateToken(user);
    }
}