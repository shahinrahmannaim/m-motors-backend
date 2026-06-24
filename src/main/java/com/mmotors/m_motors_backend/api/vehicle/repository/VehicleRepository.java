package com.mmotors.m_motors_backend.api.vehicle.repository;

import com.mmotors.m_motors_backend.api.vehicle.entity.Vehicle;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleStatus;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByType(VehicleType vehicleType);
    List<Vehicle> findByStatus(VehicleStatus vehicleStatus);
}