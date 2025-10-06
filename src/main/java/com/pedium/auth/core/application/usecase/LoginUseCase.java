package com.pedium.auth.core.application.usecase;

import com.pedium.auth.core.application.exception.InvalidCredentialsException;
import com.pedium.auth.core.application.exception.UserNotFoundException;

public interface LoginUseCase {
 
    /**
     * Realiza login do usuário
     * @param username nome do usuário
     * @param password senha em texto plano
     * @return token jwt
     */
    String login(String username, String password) throws UserNotFoundException, InvalidCredentialsException;    

}