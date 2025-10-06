package com.pedium.auth.infrastructure.security.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pedium.auth.core.application.gateway.DatabaseGateway;
import com.pedium.auth.core.domain.entity.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final DatabaseGateway databaseGateway;
    private final PasswordEncoder passwordEncoder;
 
    public CustomAuthenticationProvider(DatabaseGateway databaseGateway, PasswordEncoder passwordEncoder){
        this.databaseGateway = databaseGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = databaseGateway.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.name()))
            .collect(Collectors.toList());
            
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    
}