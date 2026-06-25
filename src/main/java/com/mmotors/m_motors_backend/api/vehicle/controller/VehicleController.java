package com.mmotors.m_motors_backend.api.vehicle.controller;

import com.mmotors.m_motors_backend.api.vehicle.dto.CreateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.UpdateVehicleRequest;
import com.mmotors.m_motors_backend.api.vehicle.dto.VehicleResponse;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleStatus;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleType;
import com.mmotors.m_motors_backend.api.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    // Creates a new vehicle. Admin only through SecurityConfig.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse createVehicle(
            @RequestBody @Validated CreateVehicleRequest request
    ) {
        return vehicleService.createVehicle(request);
    }

    // Public homepage/catalog endpoint: latest 20 AVAILABLE vehicles.
    @GetMapping
    public List<VehicleResponse> getRecentVehicles() {
        return vehicleService.getRecentVehicles();
    }

    // Returns vehicles filtered by optional query params
    @GetMapping

    public Page<VehicleResponse> searchVehicles(

            @RequestParam(required = false) String brand,

            @RequestParam(required = false) VehicleType type,

            @RequestParam(required = false) VehicleStatus status,

            @RequestParam(required = false) String fuelType,

            @RequestParam(required = false) BigDecimal minPrice,

            @RequestParam(required = false) BigDecimal maxPrice,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "12") int size,

            @RequestParam(defaultValue = "createdDate") String sortBy,

            @RequestParam(defaultValue = "desc") String direction

    ) {

        return vehicleService.searchVehicles(

                brand,

                type,

                status,

                fuelType,

                minPrice,

                maxPrice,

                page,

                size,

                sortBy,

                direction

        );

    }

    // Public detail endpoint.
    @GetMapping("/{id}")
    public VehicleResponse getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    // Updates a vehicle. Admin only through SecurityConfig.
    @PutMapping("/{id}")
    public VehicleResponse updateVehicleById(
            @PathVariable Long id,
            @RequestBody @Validated UpdateVehicleRequest request
    ) {
        return vehicleService.updateVehicle(id, request);
    }

    // Deletes a vehicle. Admin only through SecurityConfig.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}