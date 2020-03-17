package org.springframework.samples.yogogym.service;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DashboardClientServiceTest {
	
	@Autowired
	protected DashboardClientService dashboardClientService;
	
	@Test
	void shouldListTrainingByClient() {
		Collection<Training> trainings = this.dashboardClientService.listTrainingByClient(1);
		assertThat(trainings.size()).isEqualTo(1);
	}
	
	@Test
	void shouldlistRoutineByTraining() {
		List<Integer> routines = this.dashboardClientService.listRoutineByTraining(1);
		assertThat(routines.size()).isEqualTo(2);
	}
	
	@Test
	void shouldlistRepsRoutineByRoutine() {
		Integer reps = this.dashboardClientService.listRepsRoutineByRoutine(1);
		assertThat(reps).isEqualTo(3);
	}
	
	@Test
	void shouldlistExerciseByRoutine() {
		List<Exercise> exercises = this.dashboardClientService.listExerciseByRoutine(1);
		assertThat(exercises.size()).isEqualTo(6);
	}

}
