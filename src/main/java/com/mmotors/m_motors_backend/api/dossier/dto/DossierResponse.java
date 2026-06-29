package com.mmotors.m_motors_backend.api.dossier.dto;

import com.mmotors.m_motors_backend.api.dossier.entity.DossierStatus;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierType;

import java.time.Instant;

public record DossierResponse(
        Long id,

        Long userId,

        String userEmail,

        Long vehicleId,

        String vehicleBrand,

        String vehicleModel,

        DossierStatus status,

        DossierType type,

        String message,

        Instant createdDate,

        Instant updatedDate
) {}