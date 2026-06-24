package com.mmotors.m_motors_backend.api.vehicle.dto;

import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleType;

import java.math.BigDecimal;

public record CreateVehicleRequest(
        String brand,

        String model,

        Integer year,

        Integer mileage,

        BigDecimal price,

        String fuelType,

        String transmission,

        VehicleType type,

        String imageUrl,

        String description
) {}