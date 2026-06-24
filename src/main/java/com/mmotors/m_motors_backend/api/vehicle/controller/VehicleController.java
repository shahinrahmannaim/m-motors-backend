package com.mmotors.m_motors_backend.api.vehicle.controller;

import com.mmotors.m_motors_backend.api.vehicle.dto.CreateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.UpdateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.VehicleResponse;
import com.mmotors.m_motors_backend.api.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse createVehicle(@RequestBody CreateVehicleRequest request) {
        return vehicleService.createVehicle(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponse>  getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public VehicleResponse getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PutMapping("/{id}")
    public VehicleResponse updateVehicleById(@PathVariable Long id, @RequestBody @Validated UpdateVehicleRequest request) {
        return vehicleService.updateVehicle(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}