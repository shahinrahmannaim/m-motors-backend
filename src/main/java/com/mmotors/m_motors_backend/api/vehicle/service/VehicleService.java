package com.mmotors.m_motors_backend.api.vehicle.service;

import com.mmotors.m_motors_backend.api.vehicle.dto.CreateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.UpdateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.VehicleResponse;

import java.util.List;

public interface VehicleService {

    VehicleResponse createVehicle(CreateVehicleRequest request);

    List<VehicleResponse> getAllVehicles();

    VehicleResponse getVehicleById(Long id);

    VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request);

    void deleteVehicle(Long id);
}