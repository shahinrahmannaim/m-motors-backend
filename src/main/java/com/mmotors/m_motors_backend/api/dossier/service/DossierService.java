package com.mmotors.m_motors_backend.api.dossier.service;

import com.mmotors.m_motors_backend.api.dossier.dto.CreateDossierRequest;
import com.mmotors.m_motors_backend.api.dossier.dto.DossierResponse;
import com.mmotors.m_motors_backend.api.dossier.dto.UpdateDossierStatusRequest;

import java.util.List;

public interface DossierService {

    DossierResponse createDossier(CreateDossierRequest request);

    List<DossierResponse> getMyDossiers();

    DossierResponse getDossierById(Long dossierId);

    List<DossierResponse> getAllDossiers();

    DossierResponse updateDossierStatus(Long dossierId, UpdateDossierStatusRequest request);
}