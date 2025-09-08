package com.example.congestionTaxCalculator.application.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeService {

    public ZonedDateTime getAsStockolmLocalDateTime(String in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(in, formatter);
        return ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Stockholm"));
    }
}
