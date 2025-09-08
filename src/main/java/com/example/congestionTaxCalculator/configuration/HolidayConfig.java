package com.example.congestionTaxCalculator.configuration;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolidayConfig {
    @Bean
    public HolidayManager holidayManager(@Value("${holiday.country}") HolidayCalendar country) {
        return HolidayManager.getInstance(ManagerParameters.create(country));
    }
}
