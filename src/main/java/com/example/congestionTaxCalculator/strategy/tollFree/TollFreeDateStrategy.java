package com.example.congestionTaxCalculator.strategy.tollFree;

import java.time.ZonedDateTime;

public interface TollFreeDateStrategy {
    boolean isTollFreeDate(ZonedDateTime date);
}
