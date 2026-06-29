package com.mmotors.m_motors_backend.api.dossier.entity;

import com.mmotors.m_motors_backend.api.user.entity.User;
import com.mmotors.m_motors_backend.api.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "dossiers")
@Getter
@Setter
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many dossiers can belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // Many dossiers can be linked to one vehicle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private DossierStatus status;

    @Enumerated(EnumType.STRING)
    private DossierType type;

    @Column(length = 2000)

    private String message;

    private Instant createdDate;

    private Instant updatedDate;

}