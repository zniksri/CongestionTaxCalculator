package com.example.congestionTaxCalculator.strategy.days;

import java.time.LocalDate;

public interface BusinessDayStrategy {
    boolean isWeekend(LocalDate date);
    boolean isHoliday(LocalDate date);
    boolean isBusinessDay(LocalDate date);
}
