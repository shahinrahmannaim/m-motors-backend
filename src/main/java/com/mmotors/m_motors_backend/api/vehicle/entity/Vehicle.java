package com.mmotors.m_motors_backend.api.vehicle.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name= "vehicles")
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer mileage;
    private BigDecimal price;
    private Integer year;
    private String fuelType;
    private String transmission;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private String imageUrl;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;

}