package com.example.congestionTaxCalculator.configuration;

import com.example.congestionTaxCalculator.model.DefaultVehicle;
import com.example.congestionTaxCalculator.model.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class TaxCalculatorConfiguration {

    @Value("${tax-free-vehicles}")
    List<String> taxFreeVehicleNames;

    @Value("${max-tax-per-day}") int maxTaxPerDay;

    @Bean
    public List<Vehicle> taxFreeVehicles() {
        return taxFreeVehicleNames.stream().map(DefaultVehicle::of).collect(Collectors.toList());
    }

    @Bean
    public int maxTaxPerDay() {
        return maxTaxPerDay;
    }
}
