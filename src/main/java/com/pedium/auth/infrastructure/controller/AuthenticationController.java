package com.pedium.auth.infrastructure.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedium.auth.domain.usecase.LoginUseCase;
import com.pedium.auth.infrastructure.controller.dto.LoginRequest;
import com.pedium.auth.infrastructure.controller.dto.LoginResponse;
import com.pedium.auth.infrastructure.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final LoginUseCase loginUseCase;

    public AuthenticationController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.username(), request.password());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}