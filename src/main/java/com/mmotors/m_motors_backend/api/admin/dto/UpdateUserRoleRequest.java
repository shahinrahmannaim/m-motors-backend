package com.mmotors.m_motors_backend.api.admin.dto;

import com.mmotors.m_motors_backend.api.user.entity.Role;

public record UpdateUserRoleRequest(
        Role role
) {}