package com.mmotors.m_motors_backend.api.user.dto;

public record UpdateUserRequest(
        String fullName,
        String email,
        String phone
) {}