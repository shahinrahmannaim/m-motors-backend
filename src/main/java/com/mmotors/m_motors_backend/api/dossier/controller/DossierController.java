package com.mmotors.m_motors_backend.api.dossier.controller;

import com.mmotors.m_motors_backend.api.dossier.dto.CreateDossierRequest;
import com.mmotors.m_motors_backend.api.dossier.dto.DossierResponse;
import com.mmotors.m_motors_backend.api.dossier.dto.UpdateDossierStatusRequest;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierStatus;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierType;
import com.mmotors.m_motors_backend.api.dossier.service.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<DossierResponse> getAllDossiers(
            @RequestParam(required = false) DossierStatus status,
            @RequestParam(required = false) DossierType type,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return dossierService.getAllDossiers(
                status,
                type,
                email,
                page,
                size,
                sortBy,
                direction
        );
    }

    @PatchMapping("/admin/{id}/status")
    public DossierResponse updateDossierStatus(@PathVariable Long id, @RequestBody @Validated UpdateDossierStatusRequest request){
        return dossierService.updateDossierStatus(id, request);
    }


}