package com.mmotors.m_motors_backend.api.user.dto;

public record RegisterRequest(
        String fullName,
        String email,
        String password,
        String phone
) {}