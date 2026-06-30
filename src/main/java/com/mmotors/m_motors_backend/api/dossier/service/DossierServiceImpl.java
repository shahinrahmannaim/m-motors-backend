package com.mmotors.m_motors_backend.api.dossier.service;

import com.mmotors.m_motors_backend.api.common.exception.ForbiddenException;
import com.mmotors.m_motors_backend.api.common.exception.ResourceNotFoundException;
import com.mmotors.m_motors_backend.api.dossier.dto.CreateDossierRequest;
import com.mmotors.m_motors_backend.api.dossier.dto.DossierResponse;
import com.mmotors.m_motors_backend.api.dossier.dto.UpdateDossierStatusRequest;
import com.mmotors.m_motors_backend.api.dossier.entity.Dossier;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierStatus;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierType;
import com.mmotors.m_motors_backend.api.dossier.mapper.DossierMapper;
import com.mmotors.m_motors_backend.api.dossier.repository.DossierRepository;
import com.mmotors.m_motors_backend.api.dossier.specification.DossierSpecification;
import com.mmotors.m_motors_backend.api.user.entity.User;
import com.mmotors.m_motors_backend.api.user.repository.UserRepository;
import com.mmotors.m_motors_backend.api.vehicle.entity.Vehicle;
import com.mmotors.m_motors_backend.api.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DossierServiceImpl implements DossierService {

    private final DossierRepository dossierRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final DossierMapper dossierMapper;

    @Override
    public DossierResponse createDossier(CreateDossierRequest request) {
        User currentUser = getCurrentUser();

        Vehicle vehicle = vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        Dossier dossier = new Dossier();
        dossier.setUser(currentUser);
        dossier.setVehicle(vehicle);
        dossier.setType(request.dossierType());
        dossier.setMessage(request.message());
        dossier.setStatus(DossierStatus.PENDING);
        dossier.setCreatedDate(Instant.now());

        Dossier savedDossier = dossierRepository.save(dossier);

        return dossierMapper.toDossierResponse(savedDossier);
    }

    @Override
    public List<DossierResponse> getMyDossiers() {
        User currentUser = getCurrentUser();

        return dossierRepository.findByUserOrderByCreatedDateDesc(currentUser)
                .stream()
                .map(dossierMapper::toDossierResponse)
                .toList();
    }

    @Override
    public DossierResponse getDossierById(Long dossierId) {
        Dossier dossier = findDossierById(dossierId);

        checkOwnership(dossier);

        return dossierMapper.toDossierResponse(dossier);
    }

    @Override
    public Page<DossierResponse> getAllDossiers(
            DossierStatus status,
            DossierType type,
            String email,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        checkAdmin();

        Specification<Dossier> specification = Specification
                .where(DossierSpecification.hasStatus(status))
                .and(DossierSpecification.hasType(type))
                .and(DossierSpecification.hasUserEmail(email));

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return dossierRepository.findAll(specification, pageable)
                .map(dossierMapper::toDossierResponse);
    }

    @Override
    public DossierResponse updateDossierStatus(
            Long dossierId,
            UpdateDossierStatusRequest request
    ) {
        checkAdmin();

        Dossier dossier = findDossierById(dossierId);

        dossier.setStatus(request.status());
        dossier.setUpdatedDate(Instant.now());

        dossierRepository.save(dossier);

        Dossier reloadedDossier = findDossierById(dossierId);

        return dossierMapper.toDossierResponse(reloadedDossier);
    }

    private Dossier findDossierById(Long dossierId) {
        return dossierRepository.findWithUserAndVehicleById(dossierId)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier not found"));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ForbiddenException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            return user;
        }

        if (principal instanceof String email) {
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
        }

        throw new ForbiddenException("Invalid authenticated user");
    }

    private void checkOwnership(Dossier dossier) {
        User currentUser = getCurrentUser();

        boolean isOwner = dossier.getUser().getId().equals(currentUser.getId());
        boolean isAdmin = currentUser.getRole().name().equals("ADMIN");

        if (!isOwner && !isAdmin) {
            throw new ForbiddenException("You are not allowed to access this dossier");
        }
    }

    private void checkAdmin() {
        User currentUser = getCurrentUser();

        if (!currentUser.getRole().name().equals("ADMIN")) {
            throw new ForbiddenException("Only administrators can perform this action");
        }
    }
}