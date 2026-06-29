package com.mmotors.m_motors_backend.api.dossier.controller;

import com.mmotors.m_motors_backend.api.dossier.dto.CreateDossierRequest;
import com.mmotors.m_motors_backend.api.dossier.dto.DossierResponse;
import com.mmotors.m_motors_backend.api.dossier.dto.UpdateDossierStatusRequest;
import com.mmotors.m_motors_backend.api.dossier.service.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dossiers")
@RequiredArgsConstructor
public class DossierController {
    private final DossierService dossierService;

    // Logged-in user creates a dossier for a vehicle
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DossierResponse createDossier(@RequestBody @Validated CreateDossierRequest request){
        return dossierService.createDossier(request);
    }

    // Logged-in user sees his own dossiers
    @GetMapping("/me")
    public List<DossierResponse> getDossiers(){
        return  dossierService.getMyDossiers();
    }

    // Logged-in user gets one dossier by id
    @GetMapping("/{id}")
    public DossierResponse getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id);
    }

    // Admin gets all dossiers
    @GetMapping("/admin")
    public List<DossierResponse> getAllDossiers(){
        return  dossierService.getAllDossiers();

    }

    @PatchMapping("/admin/{id}/status")
    public DossierResponse updateDossierStatus(@PathVariable Long id, @RequestBody @Validated UpdateDossierStatusRequest request){
        return dossierService.updateDossierStatus(id, request);
    }


}