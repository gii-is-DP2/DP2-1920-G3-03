package org.springframework.samples.yogogym.web.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.samples.yogogym.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class EquipmentControllerE2ETests {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String CLIENT1_USERNAME = "client1";
	private static final String ADMIN1_USERNAME = "admin1";
	
	private static final int EQUIPMENT1_ID = 1;
	private static final int EQUIPMENT2_ID = 2;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientEquipmentList() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("equipments"))
				.andExpect(model().attribute("equipments", hasSize(10)))
				.andExpect(model().attribute("equipments", hasItem(allOf(hasProperty("id",equalTo(EQUIPMENT1_ID)), hasProperty("name",equalTo("Squat-Calf")),
					hasProperty("location",equalTo("Muscle and Tonification"))))))
				.andExpect(model().attribute("equipments", hasItem(allOf(hasProperty("id",equalTo(EQUIPMENT2_ID)), hasProperty("name",equalTo("Bars 30-50 mm")),
					hasProperty("location",equalTo("Muscle and Tonification"))))))
				.andExpect(view().name("mainMenu/equipments/equipmentsList"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerEquipmentList() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("equipments"))
				.andExpect(model().attribute("equipments", hasSize(10)))
				.andExpect(model().attribute("equipments", hasItem(allOf(hasProperty("id",equalTo(EQUIPMENT1_ID)), hasProperty("name",equalTo("Squat-Calf")),
					hasProperty("location",equalTo("Muscle and Tonification"))))))
				.andExpect(model().attribute("equipments", hasItem(allOf(hasProperty("id",equalTo(EQUIPMENT2_ID)), hasProperty("name",equalTo("Bars 30-50 mm")),
					hasProperty("location",equalTo("Muscle and Tonification"))))))
				.andExpect(view().name("mainMenu/equipments/equipmentsList"));
	}
	
	@WithMockUser(username=ADMIN1_USERNAME, authorities= {"admin"})
	@Test
	void testAdminEquipmentList() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("equipments"))
				.andExpect(model().attribute("equipments", hasSize(10)))
				.andExpect(model().attribute("equipments", hasItem(allOf(hasProperty("id",equalTo(EQUIPMENT1_ID)), hasProperty("name",equalTo("Squat-Calf")),
					hasProperty("location",equalTo("Muscle and Tonification"))))))
				.andExpect(model().attribute("equipments", hasItem(allOf(hasProperty("id",equalTo(EQUIPMENT2_ID)), hasProperty("name",equalTo("Bars 30-50 mm")),
					hasProperty("location",equalTo("Muscle and Tonification"))))))
				.andExpect(view().name("mainMenu/equipments/equipmentsList"));	
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientEquipmentDetails() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments/{equipmentId}",EQUIPMENT1_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("equipment"))
				.andExpect(model().attribute("equipment", hasProperty("name", is("Squat-Calf"))))
				.andExpect(model().attribute("equipment", hasProperty("location", is("Muscle and Tonification"))))
				.andExpect(view().name("mainMenu/equipments/equipmentsDetails"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerEquipmentDetails() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments/{equipmentId}",EQUIPMENT1_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("equipment"))
				.andExpect(model().attribute("equipment", hasProperty("name", is("Squat-Calf"))))
				.andExpect(model().attribute("equipment", hasProperty("location", is("Muscle and Tonification"))))
				.andExpect(view().name("mainMenu/equipments/equipmentsDetails"));
	}
	
	@WithMockUser(username=ADMIN1_USERNAME, authorities= {"admin"})
	@Test
	void testAdminEquipmentDetails() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments/{equipmentId}",EQUIPMENT1_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("equipment"))
				.andExpect(model().attribute("equipment", hasProperty("name", is("Squat-Calf"))))
				.andExpect(model().attribute("equipment", hasProperty("location", is("Muscle and Tonification"))))
				.andExpect(view().name("mainMenu/equipments/equipmentsDetails"));
	}
}