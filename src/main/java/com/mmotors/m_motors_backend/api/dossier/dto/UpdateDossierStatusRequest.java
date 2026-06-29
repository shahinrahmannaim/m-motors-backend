package com.mmotors.m_motors_backend.api.dossier.dto;

import com.mmotors.m_motors_backend.api.dossier.entity.DossierStatus;

public record UpdateDossierStatusRequest(
        DossierStatus status
) {}