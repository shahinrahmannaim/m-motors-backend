package com.mmotors.m_motors_backend.api.user.dto;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword
) {}