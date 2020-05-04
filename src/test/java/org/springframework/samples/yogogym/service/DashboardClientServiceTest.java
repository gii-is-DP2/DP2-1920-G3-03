package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

	@Autowired
	protected DashboardClientService dashboardClientService;

	@Test
	void shouldCountBodyPart() {
		List<Integer> countBodyPart = this.dashboardClientService.countBodyPart(null, "client1");
		assertThat(countBodyPart.size()).isEqualTo(3);
	}

	@Test
	void shouldNameBodyPart() {
		List<String> nameBodyPart = this.dashboardClientService.nameBodyPart(null, "client1");
		assertThat(nameBodyPart.size()).isEqualTo(3);
	}

	@Test
	void shouldCountRepetition() {
		List<Integer> countRepetitionType = this.dashboardClientService.countRepetitionType(null, "client1");
		assertThat(countRepetitionType.size()).isEqualTo(2);
	}

	@Test
	void shouldNameRepetition() {
		List<String> nameRepetitionType = this.dashboardClientService.nameRepetitionType(null, "client1");
		assertThat(nameRepetitionType.size()).isEqualTo(2);
	}

	@Test
	void shouldSumKcal() {
		Integer sumKcal = this.dashboardClientService.sumKcal(null, "client1");
		assertThat(sumKcal).isEqualTo(1600);
	}

}
