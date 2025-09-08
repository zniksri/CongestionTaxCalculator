package com.example.congestionTaxCalculator.strategy;

import com.example.congestionTaxCalculator.cityRules.GothenburgRule;
import com.example.congestionTaxCalculator.model.Vehicle;
import com.example.congestionTaxCalculator.model.taxtime.TaxTimeInterval;
import com.example.congestionTaxCalculator.strategy.tollFee.TollFeeStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TollFee implements TollFeeStrategy, GothenburgRule {
    private final List<TaxTimeInterval> taxTimeIntervals;

    @Override
    public int getTollFee(ZonedDateTime incomingTime, Vehicle vehicle) {
        return taxTimeIntervals.stream().mapToInt(i -> i.yieldTaxRate(incomingTime.toLocalTime())).sum();
    }
}
