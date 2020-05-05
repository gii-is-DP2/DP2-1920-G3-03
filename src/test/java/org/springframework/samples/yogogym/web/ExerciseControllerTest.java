package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;


@WebMvcTest(value = ExerciseController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ExerciseControllerTest {
	
	private final int testExerciseId1 = 1;
	private final int testExerciseId2 = 2;
	
	@MockBean
	private ExerciseService exerciseService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setUp()
	{
		Exercise exercise1 = new Exercise();
		exercise1.setId(testExerciseId1);
		exercise1.setName("Exercise test 1");
		exercise1.setDescription("Desc 1");
		exercise1.setKcal(100);
		exercise1.setIntensity(Intensity.LOW);
		exercise1.setRepetitionType(RepetitionType.TIME_AND_REPS);
		exercise1.setBodyPart(BodyParts.ARMS);
		exercise1.setEquipment(null);
		
		Exercise exercise2 = new Exercise();
		exercise2.setId(testExerciseId2);
		exercise2.setName("Exercise test 2");
		exercise2.setDescription("Desc 2");
		exercise2.setKcal(100);
		exercise2.setIntensity(Intensity.LOW);
		exercise2.setRepetitionType(RepetitionType.TIME_AND_REPS);
		exercise2.setBodyPart(BodyParts.ARMS);
		exercise2.setEquipment(null);
		
		Collection<Exercise> allExercises = new ArrayList<>();
		allExercises.add(exercise1);
		allExercises.add(exercise2);
		
		given(this.exerciseService.findExerciseById(testExerciseId1)).willReturn(exercise1);
		given(this.exerciseService.findExerciseById(testExerciseId2)).willReturn(exercise2);
		given(this.exerciseService.findAllExercise()).willReturn(allExercises);	
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetExercisesTrainer() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercises"))
			.andExpect(model().attribute("exercises", hasSize(2)))
			.andExpect(model().attribute("exercises", hasItem(allOf(hasProperty("name", is("Exercise test 1")),
				hasProperty("description", is("Desc 1")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.LOW)),
				hasProperty("bodyPart", equalTo(BodyParts.ARMS)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", nullValue())))))
			.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetExercisesClient() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercises"))
			.andExpect(model().attribute("exercises", hasSize(2)))
			.andExpect(model().attribute("exercises", hasItem(allOf(hasProperty("name", is("Exercise test 1")),
				hasProperty("description", is("Desc 1")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.LOW)),
				hasProperty("bodyPart", equalTo(BodyParts.ARMS)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", nullValue())))))
			.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testGetExercisesAdmin() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercises"))
			.andExpect(model().attribute("exercises", hasSize(2)))
			.andExpect(model().attribute("exercises", hasItem(allOf(hasProperty("name", is("Exercise test 1")),
				hasProperty("description", is("Desc 1")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.LOW)),
				hasProperty("bodyPart", equalTo(BodyParts.ARMS)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", nullValue())))))
			.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetExerciseDetailsTrainer() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",testExerciseId1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercise"))
			.andExpect(model().attribute("exercise", hasProperty("name", is("Exercise test 1"))))
			.andExpect(model().attribute("exercise", hasProperty("description", is("Desc 1"))))
			.andExpect(model().attribute("exercise", hasProperty("kcal", equalTo(100))))
			.andExpect(model().attribute("exercise", hasProperty("intensity", equalTo(Intensity.LOW))))
			.andExpect(model().attribute("exercise", hasProperty("bodyPart", equalTo(BodyParts.ARMS))))
			.andExpect(model().attribute("exercise", hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS))))
			.andExpect(model().attribute("exercise", hasProperty("equipment", nullValue())))
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetExerciseDetailsClient() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",testExerciseId1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercise"))
			.andExpect(model().attribute("exercise", hasProperty("name", is("Exercise test 1"))))
			.andExpect(model().attribute("exercise", hasProperty("description", is("Desc 1"))))
			.andExpect(model().attribute("exercise", hasProperty("kcal", equalTo(100))))
			.andExpect(model().attribute("exercise", hasProperty("intensity", equalTo(Intensity.LOW))))
			.andExpect(model().attribute("exercise", hasProperty("bodyPart", equalTo(BodyParts.ARMS))))
			.andExpect(model().attribute("exercise", hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS))))
			.andExpect(model().attribute("exercise", hasProperty("equipment", nullValue())))
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"));
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testGetExerciseDetailsAdmin() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",testExerciseId1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercise"))
			.andExpect(model().attribute("exercise", hasProperty("name", is("Exercise test 1"))))
			.andExpect(model().attribute("exercise", hasProperty("description", is("Desc 1"))))
			.andExpect(model().attribute("exercise", hasProperty("kcal", equalTo(100))))
			.andExpect(model().attribute("exercise", hasProperty("intensity", equalTo(Intensity.LOW))))
			.andExpect(model().attribute("exercise", hasProperty("bodyPart", equalTo(BodyParts.ARMS))))
			.andExpect(model().attribute("exercise", hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS))))
			.andExpect(model().attribute("exercise", hasProperty("equipment", nullValue())))
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"));
	}
}
