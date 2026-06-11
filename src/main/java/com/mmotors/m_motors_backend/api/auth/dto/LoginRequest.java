package com.mmotors.m_motors_backend.api.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}