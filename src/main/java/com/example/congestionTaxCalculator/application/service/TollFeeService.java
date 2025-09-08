package com.example.congestionTaxCalculator.application.service;

import com.example.congestionTaxCalculator.model.Vehicle;
import com.example.congestionTaxCalculator.strategy.tollFee.TollFeeStrategy;
import com.example.congestionTaxCalculator.strategy.tollFree.TollFreeDateStrategy;
import com.example.congestionTaxCalculator.strategy.tollFree.TollFreeVehicleStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class TollFeeService {

    private final TollFreeDateStrategy tollFreeDatestrategy;

    private final TollFreeVehicleStrategy tollFreeVehicleStrategy;

    private final TollFeeStrategy tollFeeStrategy;

    public int getTollFee(ZonedDateTime incomingTime, Vehicle vehicle) {
        if (tollFreeDatestrategy.isTollFreeDate(incomingTime) ||
                tollFreeVehicleStrategy.isTollFreeVehicle(vehicle)) return 0;

        return tollFeeStrategy.getTollFee(incomingTime, vehicle);
    }
}
