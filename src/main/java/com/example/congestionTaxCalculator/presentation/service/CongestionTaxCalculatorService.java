package com.example.congestionTaxCalculator.presentation.service;

import com.example.congestionTaxCalculator.application.service.DateTimeService;
import com.example.congestionTaxCalculator.application.service.TollFeeService;
import com.example.congestionTaxCalculator.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CongestionTaxCalculatorService {
    private final TollFeeService tollFeeService;
    private final DateTimeService dateTimeService;
    private final int maxTaxPerDay;

    public int calculate(Vehicle vehicle, List<String> dateTimesStrings) {
        return groupByDate(dateTimesStrings).values()
                .stream()
                .mapToInt(entries -> calculateDailyTax(vehicle, entries))
                .sum();
    }

    private Map<LocalDate, List<ZonedDateTime>> groupByDate(List<String> dateTimesStrings) {
        return dateTimesStrings.stream()
                .map(dateTimeService::getAsStockolmLocalDateTime)
                .sorted()
                .collect(Collectors.groupingBy(
                        ZonedDateTime::toLocalDate,
                        Collectors.toList()
                ));
    }

    private int calculateDailyTax(Vehicle vehicle, List<ZonedDateTime> sortedEntries) {
        int dailyTax = 0;

        for (int i = 0; i < sortedEntries.size(); i++) {
            ZonedDateTime current = sortedEntries.get(i);

            // Collect entries within 60 minutes
            List<ZonedDateTime> windowWithin60Minutes = sortedEntries.stream()
                    .filter(d -> !d.isBefore(current) && d.isBefore(current.plusMinutes(60)))
                    .toList();

            int maxFeeInWindow = windowWithin60Minutes.stream()
                    .mapToInt(d -> tollFeeService.getTollFee(d, vehicle))
                    .max()
                    .orElse(0);

            dailyTax += maxFeeInWindow;

            // Skip to first outside the 60-min window
            i += windowWithin60Minutes.size() - 1;
        }

        return Math.min(dailyTax, maxTaxPerDay);
    }

}
