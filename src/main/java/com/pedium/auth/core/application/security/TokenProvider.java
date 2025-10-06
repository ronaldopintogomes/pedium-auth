package com.pedium.auth.core.application.security;

import com.pedium.auth.core.domain.entity.User;

public interface TokenProvider {
    
    /**
     * Gera um token JWT ou qualquer outro tipo de token para o usuário.
     *
     * @param userId ID do usuário para quem o token será gerado
     * @return token gerado como String
     */
    public String generateToken(User user);
    
    /**
     * Valida um token JWT ou qualquer outro tipo de token do usuário.
     *
     * @param id ID do usuário que será validado o token
     * @return token gerado como String
     */
    public boolean isValidToken(String token);
}