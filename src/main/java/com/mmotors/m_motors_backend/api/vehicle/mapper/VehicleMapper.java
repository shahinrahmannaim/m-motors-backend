package com.mmotors.m_motors_backend.api.vehicle.mapper;

import com.mmotors.m_motors_backend.api.vehicle.dto.VehicleResponse;
import com.mmotors.m_motors_backend.api.vehicle.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getMileage(),
                vehicle.getPrice(),
                vehicle.getFuelType(),
                vehicle.getTransmission(),
                vehicle.getStatus(),
                vehicle.getType(),
                vehicle.getImageUrl(),
                vehicle.getDescription()
        );
    }
}