package com.example.congestionTaxCalculator.strategy;

import com.example.congestionTaxCalculator.cityRules.GothenburgRule;
import com.example.congestionTaxCalculator.strategy.days.BusinessDayStrategy;
import com.example.congestionTaxCalculator.strategy.tollFree.TollFreeDateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class TollFreeDates implements TollFreeDateStrategy, GothenburgRule {
private final BusinessDayStrategy businessDayStrategy;

    @Override
    public boolean isTollFreeDate(ZonedDateTime date) {
        return !businessDayStrategy.isBusinessDay(date.toLocalDate());
    }
}
