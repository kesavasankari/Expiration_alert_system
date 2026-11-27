package com.expirtion_alert.controller;

import com.expirtion_alert.dto.AuthResponse;
import com.expirtion_alert.dto.LoginRequest;
import com.expirtion_alert.dto.RefreshTokenRequest;
import com.expirtion_alert.dto.UserRequest;
import com.expirtion_alert.service.UserServiceImpl;
import com.expirtion_alert.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@Valid @RequestBody UserRequest request){
       return service.register(request);

    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),request.password()));

        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(request.email()),
                jwtUtil.generateRefreshToken(request.email())));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        if(!jwtUtil.validateToken(request.refreshToken())){
            throw  new RuntimeException("Invalid Token");
        }

        String email= jwtUtil.extractUsername(request.refreshToken());

        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(email),request.refreshToken()));
    }
}
