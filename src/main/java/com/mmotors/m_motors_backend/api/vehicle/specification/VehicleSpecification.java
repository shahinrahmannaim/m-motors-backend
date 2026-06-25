package com.mmotors.m_motors_backend.api.vehicle.specification;

import com.mmotors.m_motors_backend.api.vehicle.entity.Vehicle;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleStatus;
import com.mmotors.m_motors_backend.api.vehicle.entity.VehicleType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class VehicleSpecification {

    // Builds a dynamic JPA filter condition for the vehicle brand
    public static Specification<Vehicle> hasBrand(String brand) {

        return (root, query, criteriaBuilder) -> {

            if (brand == null || brand.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("brand")),
                    "%" + brand.toLowerCase() + "%"
            );
        };
    }

    // Builds a dynamic JPA filter condition for the vehicle type
    public static Specification<Vehicle> hasType(VehicleType type) {

        return (root, query, criteriaBuilder) -> {

            if (type == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    // Builds a dynamic JPA filter condition for the vehicle status
    public static Specification<Vehicle> hasStatus(VehicleStatus status) {

        return (root, query, criteriaBuilder) -> {

            if (status == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    // Builds a dynamic JPA filter condition for the fuel type
    public static Specification<Vehicle> hasFuelType(String fuelType) {

        return (root, query, criteriaBuilder) -> {

            if (fuelType == null || fuelType.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("fuelType")),
                    fuelType.toLowerCase()
            );
        };
    }

    // Builds a dynamic JPA filter condition for minimum price
    public static Specification<Vehicle> priceGreaterThanOrEqual(BigDecimal minPrice) {

        return (root, query, criteriaBuilder) -> {

            if (minPrice == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    // Builds a dynamic JPA filter condition for maximum price
    public static Specification<Vehicle> priceLessThanOrEqual(BigDecimal maxPrice) {

        return (root, query, criteriaBuilder) -> {

            if (maxPrice == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}