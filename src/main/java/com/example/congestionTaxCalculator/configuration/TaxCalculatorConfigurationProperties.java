package com.example.congestionTaxCalculator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalTime;
import java.util.List;

@ConfigurationProperties(prefix = "tax")
public record TaxCalculatorConfigurationProperties (
        List<String> freeVehiclesNames,
        int maxPerDay,
        Fees fees
) {
    public record Fees(List<Interval> intervals) {}

    public record Interval(LocalTime start, LocalTime end, int fee) {}

}
