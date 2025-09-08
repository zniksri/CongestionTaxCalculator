package com.example.congestionTaxCalculator.presentation.controller;

import com.example.congestionTaxCalculator.model.DefaultVehicle;
import com.example.congestionTaxCalculator.presentation.dto.TaxResult;
import com.example.congestionTaxCalculator.presentation.service.CongestionTaxCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequiredArgsConstructor
public class TaxCalculatorEndpoint {

    private final CongestionTaxCalculatorService calculator;

    @PostMapping("tax")
    public Mono<TaxResult> calculateTax(@RequestBody List<String> dateList) {
        return Mono.fromCallable(() -> calculator.calculate(DefaultVehicle.of("test"), dateList))
                .map(tax -> new TaxResult(String.valueOf(tax)));
    }
}
