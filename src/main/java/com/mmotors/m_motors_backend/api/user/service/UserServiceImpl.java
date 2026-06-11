package com.mmotors.m_motors_backend.api.user.service;

import com.mmotors.m_motors_backend.api.user.dto.ChangePasswordRequest;
import com.mmotors.m_motors_backend.api.user.dto.RegisterRequest;
import com.mmotors.m_motors_backend.api.user.dto.UpdateUserRequest;
import com.mmotors.m_motors_backend.api.user.dto.UserResponse;
import com.mmotors.m_motors_backend.api.user.entity.Role;
import com.mmotors.m_motors_backend.api.user.entity.User;
import com.mmotors.m_motors_backend.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPhone(request.phone());
        user.setRole(Role.USER);
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    @Override
    public UserResponse getCurrentUser() {
        User user = getAuthenticatedUser();

        return mapToUserResponse(user);
    }

    @Override
    public UserResponse updateProfile(UpdateUserRequest request) {

        User user = getAuthenticatedUser();

        if (request.fullName() != null && !request.fullName().isBlank()) {
            user.setFullName(request.fullName());
        }

        if (request.phone() != null && !request.phone().isBlank()) {
            user.setPhone(request.phone());
        }

        if (request.email() != null && !request.email().isBlank()) {

            boolean emailAlreadyExists = userRepository.existsByEmail(request.email());
            boolean emailChanged = !request.email().equals(user.getEmail());

            if (emailAlreadyExists && emailChanged) {
                throw new IllegalArgumentException("Email already exists");
            }

            user.setEmail(request.email());
        }

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }



    @Override
    public void changePassword(ChangePasswordRequest request) {

        User user = getAuthenticatedUser();

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));

        userRepository.save(user);
    }


    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getEnabled()
        );
    }


}