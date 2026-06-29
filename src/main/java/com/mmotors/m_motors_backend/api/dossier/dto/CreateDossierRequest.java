package com.mmotors.m_motors_backend.api.dossier.dto;

import com.mmotors.m_motors_backend.api.dossier.entity.DossierType;

public record CreateDossierRequest(
        Long vehicleId,
        DossierType dossierType,
        String message
) {}