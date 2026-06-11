package com.mmotors.m_motors_backend.api.auth.dto;

public record AuthResponse(
         String token,
        Long id,
        String fullName,
        String email,
        String role
) {}