package com.pedium.auth.infrastructure.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pedium.auth.domain.entity.User;
import com.pedium.auth.domain.gateway.UserGateway;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;
 
    public CustomAuthenticationProvider(UserGateway userGateway, PasswordEncoder passwordEncoder){
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userGateway.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    
}