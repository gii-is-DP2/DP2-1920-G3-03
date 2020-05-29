package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageClients;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageGuilds;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DashboardsAdminServiceTest {
	
	private static final String[] nameEquipmentTest = {"Bars 30-50 mm", "Dumbbells", "Elliptical", "Kettlebells", "Treadmill",
														"Weight-Disc 30-50 mm"};
	
	private static final Integer[] countEquipmentTest = {1, 1, 1, 2, 5, 1};

	@Autowired
	protected DashboardsAdminService dashboardService;

	@Test
	void shouldCountEquipment() {
		Integer[] count = this.dashboardService.countEquipment(1, 2020);
		assertThat(count).isEqualTo(countEquipmentTest);
	}

	@Test
	void shouldNameEquipment() {
		String[] count = this.dashboardService.nameEquipment(1, 2020);
		assertThat(count).isEqualTo(nameEquipmentTest);
	}
	
	@Test
	void shouldCountChallengesOfMonthAndYear(){
		int countChallenges = this.dashboardService.countChallengesOfMonthAndYear(10,2020);
		assertThat(countChallenges).isEqualTo(3);
	}

	@ParameterizedTest
	@ValueSource(ints = {2,4,5,6})
	void shouldNotCountChallengesOfMonthAndYear(int month){
		int countChallenges = this.dashboardService.countChallengesOfMonthAndYear(month,2020);
		assertThat(countChallenges).isEqualTo(0);
	}
	
	@Test
	void shouldCountCompletedInscriptionsOfMonthAndYear(){
		int inscriptions = this.dashboardService.countCompletedInscriptionsOfMonthAndYear(1,2020);
		assertThat(inscriptions).isEqualTo(3);
	}
	
	@Test
	void shouldNotFindCompletedInscriptionsOfMonth(){
		int inscriptions = this.dashboardService.countCompletedInscriptionsOfMonthAndYear(2,2020);
		assertThat(inscriptions).isEqualTo(0);
	}
	
	@Test
	void shouldGetChallengesNamesOfMonthAndYear(){
		String[] challengesNames = this.dashboardService.getChallengesNamesOfMonthAndYear(1, 2020);
		assertThat(challengesNames[0]).isEqualTo("Challenge1");
	}
	
	@Test
	void shouldGetTopClientOfMonthAndYear(){
		DashboardAdminChallengesTopClient topClient = this.dashboardService.getTopClient(1, 2020);
		assertThat(topClient.getUsername()).isEqualTo("Sofia Victoria Obeso");
		assertThat(topClient.getEmail()).isEqualTo("soviob@yogogym.com");
		assertThat(topClient.getPoints()).isEqualTo(20);
	}
	
	@Test
	void shouldGetTopGuildOfMonthAndYear(){
		DashboardAdminChallengesTopGuild topGuild = this.dashboardService.getTopGuild(1, 2020);
		assertThat(topGuild.getGuild()).isEqualTo("Gym for Dummies");
		assertThat(topGuild.getPoints()).isEqualTo(30);
	}
	
	@Test
	void shouldGetPercentageClients() {
		List<DashboardAdminChallengesPercentageClients> pClients = this.dashboardService.getPercentageClients(1, 2020);
		assertThat(pClients.get(0).getChallengeName()).isEqualTo("Challenge1");
		assertThat(Math.round(pClients.get(0).getPercentageClients())).isEqualTo(Math.round(0.09090909090909091));
	}
	
	@Test
	void shouldGetPercentageGuilds() {
		List<DashboardAdminChallengesPercentageGuilds> pGuilds = this.dashboardService.getPercentageGuilds(1, 2020);
		assertThat(pGuilds.get(0).getChallengeName()).isEqualTo("Challenge1");
		assertThat(Math.round(pGuilds.get(0).getPercentageGuilds())).isEqualTo(Math.round(0.3333333333333333));
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
