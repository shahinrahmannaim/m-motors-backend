package com.mmotors.m_motors_backend.api.user.service;

import com.mmotors.m_motors_backend.api.auth.dto.LoginRequest;
import com.mmotors.m_motors_backend.api.user.dto.ChangePasswordRequest;
import com.mmotors.m_motors_backend.api.user.dto.RegisterRequest;
import com.mmotors.m_motors_backend.api.user.dto.UpdateUserRequest;
import com.mmotors.m_motors_backend.api.user.dto.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);
    UserResponse getCurrentUser();
    UserResponse updateProfile(UpdateUserRequest request);
    void changePassword(ChangePasswordRequest request);
}