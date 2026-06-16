package com.mmotors.m_motors_backend.api.admin.service;

import com.mmotors.m_motors_backend.api.admin.dto.UpdateUserRoleRequest;
import com.mmotors.m_motors_backend.api.admin.dto.UpdateUserStatusRequest;
import com.mmotors.m_motors_backend.api.user.dto.UserResponse;

import java.util.List;

public interface AdminService {

    String dashboard();

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUserRole(Long id, UpdateUserRoleRequest request);

    UserResponse updateUserStatus(Long id, UpdateUserStatusRequest request);
}