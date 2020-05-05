package org.springframework.samples.yogogym.web.e2e;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class ExerciseControllerE2ETest {
	
	private static final int EXERCISE1_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetExercisesTrainer() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercises"))
			.andExpect(model().attribute("exercises", hasSize(65)))
			.andExpect(model().attribute("exercises", hasItem(allOf(hasProperty("name", is("Squat")),
				hasProperty("description", is("Crouch and stand up")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.MODERATED)),
				hasProperty("bodyPart", equalTo(BodyParts.LOWER_TRUNK)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", allOf(hasProperty("name", is("Squat-Calf")),hasProperty("location", is("Muscle and Tonification"))))))))
			.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetExercisesClient() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercises"))
			.andExpect(model().attribute("exercises", hasSize(65)))
			.andExpect(model().attribute("exercises", hasItem(allOf(hasProperty("name", is("Squat")),
				hasProperty("description", is("Crouch and stand up")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.MODERATED)),
				hasProperty("bodyPart", equalTo(BodyParts.LOWER_TRUNK)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", allOf(hasProperty("name", is("Squat-Calf")),hasProperty("location", is("Muscle and Tonification"))))))))
			.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testGetExercisesAdmin() throws Exception
	{		
		mockMvc.perform(get("/mainMenu/exercises"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercises"))
			.andExpect(model().attribute("exercises", hasSize(65)))
			.andExpect(model().attribute("exercises", hasItem(allOf(hasProperty("name", is("Squat")),
				hasProperty("description", is("Crouch and stand up")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.MODERATED)),
				hasProperty("bodyPart", equalTo(BodyParts.LOWER_TRUNK)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", allOf(hasProperty("name", is("Squat-Calf")),hasProperty("location", is("Muscle and Tonification"))))))))
			.andExpect(view().name("mainMenu/exercises/exercisesList"));
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetExerciseDetailsTrainer() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",EXERCISE1_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercise"))
			.andExpect(model().attribute("exercise", allOf(hasProperty("name", is("Squat")),
				hasProperty("description", is("Crouch and stand up")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.MODERATED)),
				hasProperty("bodyPart", equalTo(BodyParts.LOWER_TRUNK)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", allOf(hasProperty("name", is("Squat-Calf")),hasProperty("location", is("Muscle and Tonification")))))))
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetExerciseDetailsClient() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",EXERCISE1_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercise"))
			.andExpect(model().attribute("exercise", allOf(hasProperty("name", is("Squat")),
				hasProperty("description", is("Crouch and stand up")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.MODERATED)),
				hasProperty("bodyPart", equalTo(BodyParts.LOWER_TRUNK)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", allOf(hasProperty("name", is("Squat-Calf")),hasProperty("location", is("Muscle and Tonification")))))))
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"));
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testGetExerciseDetailsAdmin() throws Exception
	{
		mockMvc.perform(get("/mainMenu/exercises/{exerciseId}",EXERCISE1_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("exercise"))
			.andExpect(model().attribute("exercise", allOf(hasProperty("name", is("Squat")),
				hasProperty("description", is("Crouch and stand up")),hasProperty("kcal", equalTo(100)),
				hasProperty("intensity", equalTo(Intensity.MODERATED)),
				hasProperty("bodyPart", equalTo(BodyParts.LOWER_TRUNK)),
				hasProperty("repetitionType", equalTo(RepetitionType.TIME_AND_REPS)),
				hasProperty("equipment", allOf(hasProperty("name", is("Squat-Calf")),hasProperty("location", is("Muscle and Tonification")))))))
			.andExpect(view().name("mainMenu/exercises/exerciseDetails"));
	}

}