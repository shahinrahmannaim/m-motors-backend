package com.mmotors.m_motors_backend.api.admin.controller;

import com.mmotors.m_motors_backend.api.admin.dto.UpdateUserRoleRequest;
import com.mmotors.m_motors_backend.api.admin.dto.UpdateUserStatusRequest;
import com.mmotors.m_motors_backend.api.admin.service.AdminService;
import com.mmotors.m_motors_backend.api.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return adminService.dashboard();
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @PatchMapping("/users/{id}/role")
    public UserResponse updateUserRole(
            @PathVariable Long id,
            @RequestBody UpdateUserRoleRequest request
    ) {
        return adminService.updateUserRole(id, request);
    }

    @PatchMapping("/users/{id}/status")
    public UserResponse updateUserStatus(
            @PathVariable Long id,
            @RequestBody UpdateUserStatusRequest request
    ) {
        return adminService.updateUserStatus(id, request);
    }
}