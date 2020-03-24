package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ExerciseController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ExerciseControllerTest {
	
	private final int testExerciseId = 1;
	
	@MockBean
	private ExerciseService exerciseService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setUp()
	{
		Exercise exercise = new Exercise();
		exercise.setId(1);
		exercise.setName("Exercise test");
		exercise.setDescription("Desc");
		exercise.setKcal(100);
		exercise.setIntensity(Intensity.LOW);
		exercise.setRepetitionType(RepetitionType.TIME_AND_REPS);
		exercise.setBodyPart(BodyParts.ARMS);
		
		Collection<Exercise> allExercises = new ArrayList<>();
		allExercises.add(exercise);
		
		given(this.exerciseService.findExerciseById(testExerciseId)).willReturn(exercise);
		given(this.exerciseService.findAllExercise()).willReturn(allExercises);	
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetExercisesTrainer() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
		.andExpect(status().isOk())
		.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetExercisesClient() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
		.andExpect(status().isOk())
		.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testGetExercisesAdmin() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
		.andExpect(status().isOk())
		.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetExerciseDetailsTrainer() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",testExerciseId))
			.andExpect(status().isOk())
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"))
			.andDo(print());
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetExerciseDetailsClient() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",testExerciseId))
			.andExpect(status().isOk())
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"))
			.andDo(print());
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testGetExerciseDetailsAdmin() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",testExerciseId))
			.andExpect(status().isOk())
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"))
			.andDo(print());
	}
}
