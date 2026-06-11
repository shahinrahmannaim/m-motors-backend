package com.mmotors.m_motors_backend.api.auth.controller;

import com.mmotors.m_motors_backend.api.auth.dto.AuthResponse;
import com.mmotors.m_motors_backend.api.auth.dto.LoginRequest;
import com.mmotors.m_motors_backend.api.auth.service.AuthService;
import com.mmotors.m_motors_backend.api.user.dto.RegisterRequest;
import com.mmotors.m_motors_backend.api.user.dto.UserResponse;
import com.mmotors.m_motors_backend.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody RegisterRequest request){
        return userService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}