package com.mmotors.m_motors_backend.api.dossier.mapper;

import com.mmotors.m_motors_backend.api.dossier.dto.DossierResponse;
import com.mmotors.m_motors_backend.api.dossier.entity.Dossier;
import org.springframework.stereotype.Component;

@Component
public class DossierMapper {

    public DossierResponse toDossierResponse(Dossier dossier) {
        return new DossierResponse(
                dossier.getId(),
                dossier.getUser().getId(),
                dossier.getUser().getEmail(),
                dossier.getVehicle().getId(),
                dossier.getVehicle().getBrand(),
                dossier.getVehicle().getModel(),
                dossier.getStatus(),
                dossier.getType(),
                dossier.getMessage(),
                dossier.getCreatedDate(),
                dossier.getUpdatedDate()
        );
    }
}