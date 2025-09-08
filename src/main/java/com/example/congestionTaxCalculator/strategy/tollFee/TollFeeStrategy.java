package com.example.congestionTaxCalculator.strategy.tollFee;

import com.example.congestionTaxCalculator.model.Vehicle;

import java.time.ZonedDateTime;

public interface TollFeeStrategy {
    int getTollFee(ZonedDateTime incomingTime, Vehicle vehicle);
}
