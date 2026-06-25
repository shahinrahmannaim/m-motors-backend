package com.mmotors.m_motors_backend.api.vehicle.service;

import com.mmotors.m_motors_backend.api.vehicle.dto.CreateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.UpdateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.VehicleResponse;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleStatus;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {

    VehicleResponse createVehicle(CreateVehicleRequest request);

    List<VehicleResponse> getRecentVehicles();

    Page<VehicleResponse> searchVehicles(
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
    );

    VehicleResponse getVehicleById(Long id);

    VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request);

    void deleteVehicle(Long id);
}