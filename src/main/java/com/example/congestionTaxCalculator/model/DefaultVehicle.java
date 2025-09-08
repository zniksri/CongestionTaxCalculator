package com.example.congestionTaxCalculator.model;

import java.util.Objects;

public record DefaultVehicle(String vehicleType) implements Vehicle{
    public static DefaultVehicle of(String vehicleType) {
        Objects.requireNonNull(vehicleType, "vehicleType cannot be null");
        return new DefaultVehicle(vehicleType.toLowerCase());
    }
}
