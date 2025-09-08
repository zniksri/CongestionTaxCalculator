package com.example.congestionTaxCalculator.application;

import com.example.congestionTaxCalculator.cityRules.GothenburgRule;
import com.example.congestionTaxCalculator.model.Vehicle;
import com.example.congestionTaxCalculator.strategy.tollFree.TollFreeVehicleStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TollFreeVehicles implements TollFreeVehicleStrategy, GothenburgRule {
    private final List<Vehicle> taxFreeVehicles;

    @Override
    public boolean isTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;
        return taxFreeVehicles.contains(vehicle);
    }
}
