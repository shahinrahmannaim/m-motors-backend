package com.mmotors.m_motors_backend.api.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name" ,nullable = false,length = 120)
    private String fullName;

    @Column(nullable = false,unique = true,length = 180)
    private String email;

    @Column(nullable = false,length = 80)
    private String password;

    @Column(nullable = false,unique = true,length = 10)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 50)
    private Role role=Role.USER;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

}