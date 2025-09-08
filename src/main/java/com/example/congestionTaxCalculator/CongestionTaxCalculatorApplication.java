package com.example.congestionTaxCalculator;

import com.example.congestionTaxCalculator.configuration.TaxCalculatorConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TaxCalculatorConfigurationProperties.class)
public class CongestionTaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongestionTaxCalculatorApplication.class, args);
	}

}
