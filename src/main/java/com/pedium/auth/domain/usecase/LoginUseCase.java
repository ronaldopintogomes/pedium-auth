package com.pedium.auth.domain.usecase;

import com.pedium.auth.domain.exception.InvalidCredentialsException;
import com.pedium.auth.domain.exception.UserNotFoundException;

public interface LoginUseCase {

    String login(String username, String password) throws UserNotFoundException, InvalidCredentialsException;    
} 