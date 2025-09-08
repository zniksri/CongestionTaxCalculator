package com.example.congestionTaxCalculator.strategy.tollFree;

import com.example.congestionTaxCalculator.model.Vehicle;

public interface TollFreeVehicleStrategy {
    boolean isTollFreeVehicle(Vehicle vehicle);
}
