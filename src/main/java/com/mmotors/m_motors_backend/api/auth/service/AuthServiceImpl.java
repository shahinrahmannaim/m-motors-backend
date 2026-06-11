package com.mmotors.m_motors_backend.api.auth.service;

import com.mmotors.m_motors_backend.api.auth.dto.AuthResponse;
import com.mmotors.m_motors_backend.api.auth.dto.LoginRequest;
import com.mmotors.m_motors_backend.api.security.JwtService;
import com.mmotors.m_motors_backend.api.user.entity.User;
import com.mmotors.m_motors_backend.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(()->new IllegalArgumentException("Invalid Credentials"));
        boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPassword());
        if(!passwordMatches){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()

        );
    }
}