package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.service.AuthoritiesService;
import org.springframework.samples.yogogym.service.EquipmentService;
import org.springframework.context.annotation.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@WebMvcTest(value = EquipmentController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class EquipmentControllerTests {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String CLIENT1_USERNAME = "client1";
	private static final String ADMIN1_USERNAME = "admin1";
	
	private static final int EQUIPMENT1_ID = 1;
	private static final int EQUIPMENT2_ID = 2;
	
	@MockBean
	private EquipmentService equipmentService;
	
	@MockBean
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setup() {
		
		Equipment equipment1 = new Equipment();
		equipment1.setId(EQUIPMENT1_ID);
		equipment1.setName("Squat-Calf");
		equipment1.setLocation("Muscle and Tonification");
		
		Equipment equipment2 = new Equipment();
		equipment2.setId(EQUIPMENT2_ID);
		equipment2.setName("Bars 30-50 mm");
		equipment2.setLocation("Muscle and Tonification");
		
		Collection<Equipment> equipments = new ArrayList<>();
		equipments.add(equipment1);
		equipments.add(equipment2);
		
		given(this.equipmentService.findAllEquipment()).willReturn(equipments);
		given(this.equipmentService.findEquipmentById(EQUIPMENT1_ID)).willReturn(equipment1);
		given(this.equipmentService.findEquipmentById(EQUIPMENT2_ID)).willReturn(equipment2);
		
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientEquipmentList() throws Exception {
		mockMvc.perform(get("/mainMenu/equipments"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("equipments"))
				.andExpect(model().attribute("equipments", hasSize(2)))
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
				.andExpect(model().attribute("equipments", hasSize(2)))
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
				.andExpect(model().attribute("equipments", hasSize(2)))
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