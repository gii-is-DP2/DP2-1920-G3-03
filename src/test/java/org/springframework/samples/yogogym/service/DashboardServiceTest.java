package org.springframework.samples.yogogym.service;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DashboardServiceTest {
	
	@Autowired
	protected DashboardService dashboardService;
	
	@Test
	void shouldEquipmentControl() {
		Collection<Training> trainings = this.dashboardService.equipmentControl();
		assertThat(trainings.size()).isEqualTo(4);
	}
	
	@Test
	void shouldListRoutine() {
		List<Integer> routines = this.dashboardService.listRoutine(1);
		assertThat(routines.size()).isEqualTo(2);
	}
	
	@Test
	void shouldListRepsRoutine() {
		Integer reps = this.dashboardService.listRepsRoutine(1);
		assertThat(reps).isEqualTo(4);
	}
	
	@Test
	void shouldListExercise() {
		List<Integer> exercises = this.dashboardService.listExercise(1);
		assertThat(exercises.size()).isEqualTo(2);
	}
	
	@Test
	void shouldListIdEquipment() {
		Integer equipment = this.dashboardService.listIdEquipment(1);
		assertThat(equipment).isEqualTo(1);
	}
	
	@Test
	void shouldListNameEquipment() {
		String name = this.dashboardService.listNameEquipment(1);
		assertThat(name).isEqualTo("Gemelos_10000");
	}
}
