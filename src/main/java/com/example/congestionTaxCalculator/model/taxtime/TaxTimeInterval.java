package com.example.congestionTaxCalculator.model.taxtime;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@AllArgsConstructor
public class TaxTimeInterval {

    public static TaxTimeInterval of(String start, String end, int fee) {
        return new TaxTimeInterval(
                Objects.requireNonNull(start, "start must not be null"),
                Objects.requireNonNull(end, "end must not be null"),
                fee
        );
    }

    private static final DateTimeFormatter PARSER = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final int taxRate;

    TaxTimeInterval(String startTime, String endTime, int taxRate) {
        this.startTime = parse(startTime);
        this.endTime = parse(endTime);
        this.taxRate = taxRate;
    }

    private LocalTime parse(String timeStr) {
        Objects.requireNonNull(timeStr, "timeStr must not be null");
        return LocalTime.parse(timeStr, PARSER);
    }

    boolean isInTimeInterval(String timeStr) {
        final LocalTime zonedTime = parse(timeStr);
        return isInTimeInterval(zonedTime);
    }

    boolean isInTimeInterval(LocalTime time) {
        return time.isAfter(startTime.minusMinutes(1))
                && time.isBefore(endTime.plusMinutes(1));
    }

    public int yieldTaxRate(String time) {
        return isInTimeInterval(time) ? taxRate : 0;
    }

    public int yieldTaxRate(LocalTime time) {
        return isInTimeInterval(time) ? taxRate : 0;
    }
}
