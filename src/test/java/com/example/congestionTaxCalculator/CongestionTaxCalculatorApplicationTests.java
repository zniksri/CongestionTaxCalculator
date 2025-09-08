package com.example.congestionTaxCalculator;

import com.example.congestionTaxCalculator.strategy.TollFreeDates;
import com.example.congestionTaxCalculator.strategy.TollFreeVehicles;
import com.example.congestionTaxCalculator.configuration.TaxCalculatorConfigurationProperties;
import com.example.congestionTaxCalculator.model.DefaultVehicle;
import com.example.congestionTaxCalculator.strategy.days.BusinessDayStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableConfigurationProperties(TaxCalculatorConfigurationProperties.class)
class CongestionTaxCalculatorApplicationTests {

	@Autowired
	private TollFreeVehicles tollFreeVehicles;

	@Autowired
	private TaxCalculatorConfigurationProperties config;



	@Test
	void vehicleconfigPropertyTest() {
		var vehicles = config.maxPerDay();
var names = config.freeVehiclesNames();
		names.forEach(System.out::println);
	}



	@Test
	void vehicleListTest() {
var vehicles = tollFreeVehicles.isTollFreeVehicle(new DefaultVehicle("test"));

System.out.println(vehicles);
	}

	@Test
	void feesListTest() {
		var fees = config.fees().intervals();

		fees.forEach(interval ->
				System.out.printf("Fee=%d, Start=%s, End=%s%n",
						interval.fee(),
						interval.start(),
						interval.end()
				)
		);
	}


	@Autowired
	private TollFreeDates tollFreeDates;

	@Autowired
	private BusinessDayStrategy businessDayStrategy;

	@Test
	void givenWeekend_whenCheckingBusinessDay_thenFalse() {
		ZonedDateTime saturday = ZonedDateTime.of(2024, 9, 7, 10, 0, 0, 0, ZoneId.of("Europe/Berlin"));
		assertThat(tollFreeDates.isTollFreeDate(saturday)).isFalse();
	}

	@Test
	void givenHoliday_whenCheckingBusinessDay_thenFalse() {
		// Christmas Day in Germany
		ZonedDateTime christmas = ZonedDateTime.of(2024, 12, 25, 10, 0, 0, 0, ZoneId.of("Europe/Berlin"));
		assertThat(businessDayStrategy.isBusinessDay(christmas.toLocalDate())).isFalse();
	}

	@Test
	void givenNormalWeekday_whenCheckingBusinessDay_thenTrue() {
		ZonedDateTime tuesday = ZonedDateTime.of(2024, 9, 10, 10, 0, 0, 0, ZoneId.of("Europe/Berlin"));
		assertThat(businessDayStrategy.isBusinessDay(tuesday.toLocalDate())).isTrue();
	}

}
