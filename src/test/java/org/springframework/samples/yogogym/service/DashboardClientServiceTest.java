package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DashboardClientServiceTest {
	
	private static final String[] nameBodyPartTest = {"UPPER_TRUNK", "ARMS", "ALL"};
	
	private static final Integer[] countBodyPartTest = {1, 4, 6};
	
	private static final String[] nameRepetitionTypeTest = {"TIME", "TIME_AND_REPS"};
	
	private static final Integer[] countRepetitionTypeTest = {6, 5};
	
	private static final Integer sumKcalTest = 1100;

	@Autowired
	protected DashboardClientService dashboardClientService;

	@Test
	void shouldCountBodyPart() {
		Integer[] countBodyPart = this.dashboardClientService.countBodyPart(1, 2020, "client1");
		assertThat(countBodyPart).isEqualTo(countBodyPartTest);
	}

	@Test
	void shouldNameBodyPart() {
		String[] nameBodyPart = this.dashboardClientService.nameBodyPart(1, 2020, "client1");
		assertThat(nameBodyPart).isEqualTo(nameBodyPartTest);
	}

	@Test
	void shouldCountRepetition() {
		Integer[] countRepetitionType = this.dashboardClientService.countRepetitionType(1, 2020, "client1");
		assertThat(countRepetitionType).isEqualTo(countRepetitionTypeTest);
	}

	@Test
	void shouldNameRepetition() {
		String[] nameRepetitionType = this.dashboardClientService.nameRepetitionType(1, 2020, "client1");
		assertThat(nameRepetitionType).isEqualTo(nameRepetitionTypeTest);
	}

	@Test
	void shouldSumKcal() {
		Integer sumKcal = this.dashboardClientService.sumKcal(1, 2020, "client1");
		assertThat(sumKcal).isEqualTo(sumKcalTest);
	}

}
