package com.example.congestionTaxCalculator.application.service;

import com.example.congestionTaxCalculator.strategy.days.BusinessDayStrategy;
import de.focus_shift.jollyday.core.HolidayManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JollyHolidayService implements BusinessDayStrategy {
    private final HolidayManager holidayManager;

    public boolean isWeekend(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(LocalDate date) {
        return holidayManager.isHoliday(date);
    }

    public boolean isBusinessDay(LocalDate date) {
        return !isWeekend(date) && !isHoliday(date);
    }
}
