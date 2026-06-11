package com.mmotors.m_motors_backend.api.user.controller;

import com.mmotors.m_motors_backend.api.user.dto.ChangePasswordRequest;
import com.mmotors.m_motors_backend.api.user.dto.UpdateUserRequest;
import com.mmotors.m_motors_backend.api.user.dto.UserResponse;
import com.mmotors.m_motors_backend.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PutMapping("/me")
    public UserResponse updateProfile(
            @RequestBody UpdateUserRequest request
    ) {
        return userService.updateProfile(request);
    }

    @PutMapping("/me/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(
            @RequestBody ChangePasswordRequest request
    ) {
        userService.changePassword(request);
    }
}