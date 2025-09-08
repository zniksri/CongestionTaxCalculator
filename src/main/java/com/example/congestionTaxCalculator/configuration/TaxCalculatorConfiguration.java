package com.example.congestionTaxCalculator.configuration;

import com.example.congestionTaxCalculator.model.DefaultVehicle;
import com.example.congestionTaxCalculator.model.Vehicle;
import com.example.congestionTaxCalculator.model.taxtime.TaxTimeInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class TaxCalculatorConfiguration {

    @Autowired
    TaxCalculatorConfigurationProperties taxCalculatorConfigurationProperties;

    @Bean
    public List<Vehicle> taxFreeVehicles() {
        return taxCalculatorConfigurationProperties.freeVehiclesNames().stream().map(DefaultVehicle::of).collect(Collectors.toList());
    }

    @Bean
    public int maxTaxPerDay() {
        return taxCalculatorConfigurationProperties.maxPerDay();
    }

    @Bean
    public List<TaxTimeInterval> taxFeeLines() {
        return taxCalculatorConfigurationProperties.fees().intervals().stream()
                .map(interval -> new TaxTimeInterval(
                        interval.start(),
                        interval.end(),
                        interval.fee()
                ))
                .toList();
    }


}
