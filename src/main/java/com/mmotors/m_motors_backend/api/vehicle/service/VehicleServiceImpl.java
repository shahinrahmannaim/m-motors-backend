package com.mmotors.m_motors_backend.api.vehicle.service;

import com.mmotors.m_motors_backend.api.vehicle.dto.CreateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.UpdateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.VehicleResponse;
import com.mmotors.m_motors_backend.api.vehicle.entity.Vehicle;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleStatus;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleType;
import com.mmotors.m_motors_backend.api.vehicle.mapper.VehicleMapper;
import com.mmotors.m_motors_backend.api.vehicle.repository.VehicleRepository;
import com.mmotors.m_motors_backend.api.vehicle.specification.VehicleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse createVehicle(CreateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(request.brand());
        vehicle.setModel(request.model());
        vehicle.setYear(request.year());
        vehicle.setMileage(request.mileage());
        vehicle.setPrice(request.price());
        vehicle.setFuelType(request.fuelType());
        vehicle.setTransmission(request.transmission());
        vehicle.setType(request.type());
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicle.setImageUrl(request.imageUrl());
        vehicle.setDescription(request.description());
        vehicle.setCreatedDate(Instant.now());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(savedVehicle);
    }

    @Override
    public List<VehicleResponse> getRecentVehicles() {
        Specification<Vehicle> specification =
                VehicleSpecification.hasStatus(VehicleStatus.AVAILABLE);

        return vehicleRepository.findAll(
                        specification,
                        PageRequest.of(0, 20, Sort.by("createdDate").descending())
                )
                .stream()
                .map(vehicleMapper::toResponse)
                .toList();
    }

    @Override
    public Page<VehicleResponse> searchVehicles(
            String brand,
            VehicleType type,
            VehicleStatus status,
            String fuelType,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        VehicleStatus effectiveStatus =
                (status != null) ? status : VehicleStatus.AVAILABLE;

        Specification<Vehicle> specification = Specification
                .where(VehicleSpecification.hasStatus(effectiveStatus))
                .and(VehicleSpecification.hasBrand(brand))
                .and(VehicleSpecification.hasType(type))
                .and(VehicleSpecification.hasFuelType(fuelType))
                .and(VehicleSpecification.priceGreaterThanOrEqual(minPrice))
                .and(VehicleSpecification.priceLessThanOrEqual(maxPrice));

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return vehicleRepository.findAll(specification, pageable)
                .map(vehicleMapper::toResponse);
    }

    @Override
    public VehicleResponse getVehicleById(Long id) {
        Vehicle vehicle = findVehicleById(id);
        return vehicleMapper.toResponse(vehicle);
    }

    @Override
    public VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request) {
        Vehicle vehicle = findVehicleById(id);

        if (request.brand() != null && !request.brand().isBlank()) {
            vehicle.setBrand(request.brand());
        }

        if (request.model() != null && !request.model().isBlank()) {
            vehicle.setModel(request.model());
        }

        if (request.year() != null) {
            vehicle.setYear(request.year());
        }

        if (request.mileage() != null) {
            vehicle.setMileage(request.mileage());
        }

        if (request.price() != null) {
            vehicle.setPrice(request.price());
        }

        if (request.fuelType() != null && !request.fuelType().isBlank()) {
            vehicle.setFuelType(request.fuelType());
        }

        if (request.transmission() != null && !request.transmission().isBlank()) {
            vehicle.setTransmission(request.transmission());
        }

        if (request.status() != null) {
            vehicle.setStatus(request.status());
        }

        if (request.type() != null) {
            vehicle.setType(request.type());
        }

        if (request.imageUrl() != null && !request.imageUrl().isBlank()) {
            vehicle.setImageUrl(request.imageUrl());
        }

        if (request.description() != null && !request.description().isBlank()) {
            vehicle.setDescription(request.description());
        }

        vehicle.setUpdatedDate(Instant.now());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(updatedVehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = findVehicleById(id);
        vehicleRepository.delete(vehicle);
    }

    private Vehicle findVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
    }
}