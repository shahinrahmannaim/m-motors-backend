package com.mmotors.m_motors_backend.api.dossier.service;

import com.mmotors.m_motors_backend.api.dossier.dto.CreateDossierRequest;
import com.mmotors.m_motors_backend.api.dossier.dto.DossierResponse;
import com.mmotors.m_motors_backend.api.dossier.dto.UpdateDossierStatusRequest;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierStatus;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DossierService {

    DossierResponse createDossier(CreateDossierRequest request);

    List<DossierResponse> getMyDossiers();

    DossierResponse getDossierById(Long dossierId);

    Page<DossierResponse> getAllDossiers(
            DossierStatus status,
            DossierType type,
            String email,
            int page,
            int size,
            String sortBy,
            String direction
    );

    DossierResponse updateDossierStatus(Long dossierId, UpdateDossierStatusRequest request);
}