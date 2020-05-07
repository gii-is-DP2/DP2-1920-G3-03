package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DashboardsAdminServiceTest {

	@Autowired
	protected DashboardsAdminService dashboardService;

	@Test
	void shouldCountEquipment() {
		List<Integer> count = this.dashboardService.countEquipment(3000);
		assertThat(count.size()).isEqualTo(6);
	}

	@Test
	void shouldNameEquipment() {
		List<String> count = this.dashboardService.nameEquipment(3000);
		assertThat(count.size()).isEqualTo(6);
	}
	
	@Test
	void shouldGetChallengesOfMonth(){
		Collection<Challenge> challenges = this.dashboardService.getChallengesOfMonth(10);
		assertThat(challenges.size()).isEqualTo(3);
	}

	@ParameterizedTest
	@ValueSource(ints = {2,4,5,6})
	void shouldNotFindChallengesOfMonth(int month){
		Collection<Challenge> challenges = this.dashboardService.getChallengesOfMonth(month);
		assertThat(challenges.size()).isEqualTo(0);
	}
	
	@Test
	void shouldGetCompletedInscriptionsOfMonth(){
		Collection<Inscription> inscriptions = this.dashboardService.findCompletedInscriptionsThisMonth(1);
		assertThat(inscriptions.size()).isEqualTo(1);
	}
	
	@Test
	void shouldNotFindCompletedInscriptionsOfMonth(){
		Collection<Inscription> inscriptions = this.dashboardService.findCompletedInscriptionsThisMonth(2);
		assertThat(inscriptions.size()).isEqualTo(0);
	}
	
	@Test
	void shouldCountClients() {
		Integer count = this.dashboardService.countClients();
		assertThat(count).isEqualTo(11);
	}
	
	@Test
	void shouldCountTrainers() {
		Integer count = this.dashboardService.countTrainers();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	void shouldCountClientsPerGuild() {
		List<Integer> list = this.dashboardService.countClientsPerGuild();
		assertThat(list.size()).isEqualTo(4);
	}

}
