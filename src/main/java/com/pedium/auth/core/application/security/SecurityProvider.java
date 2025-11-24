package com.pedium.auth.core.application.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.pedium.auth.core.domain.entity.User;

public interface SecurityProvider {
    
    /**
     * @param input Parametro que será criptografado
     * @param cipherInstance Instancia do tipo do algoritmo que será usado para cifrar o parametro input
     * @return Retornará o parametro input como string criptografado 
     */
    public String generateHash(String input, CipherAlgorithmType cipherInstance);

    /**
     * @param input Parametro externo que será verificado
     * @param toCompare Parametro interno que serar verificado
     * @param cipherInstance Algoritmo usado para realizar a verificacao
     * @return Retornará verdadeiro ou falso
     */
    public boolean verifyHash(String input, String toCompare, CipherAlgorithmType cipherInstance);
    
    /**
     * @param user Parametro de entidade User será usado para criar token
     * @return Token gerado como String
     */
    public String generateToken(User user);
    
    /**
     * @param token Parametro que será validado
     * @return Retornará verdadeiro ou falso
     */
    public boolean isValidToken(String token);

    public void setToken(String token);

    public String getClaimNameFromToken();

    public List<GrantedAuthority> getClaimRolesFromToken();
}