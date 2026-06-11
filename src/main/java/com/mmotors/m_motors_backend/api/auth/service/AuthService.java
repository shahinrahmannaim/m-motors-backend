package com.mmotors.m_motors_backend.api.auth.service;

import com.mmotors.m_motors_backend.api.auth.dto.AuthResponse;
import com.mmotors.m_motors_backend.api.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}