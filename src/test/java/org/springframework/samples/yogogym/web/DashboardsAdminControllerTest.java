package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DashboardsAdminService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

public class DashboardsAdminControllerTest {

	private static final Integer[] COUNT = { 1 };

	private static final String[] NAME = { "prueba" };

	@Nested
	@DisplayName("Admin test with exercises")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestCompleted {

		@MockBean
		private DashboardsAdminService dashboardsAdminService;
		
		@MockBean
		private ClientService clientService;
		
		@MockBean
		private InscriptionService inscriptionService;
		
		@MockBean
		private GuildService guildService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);
			List<String> listName = new ArrayList<>();
			listName.add("prueba");

			given(this.dashboardsAdminService.countEquipment(7)).willReturn(listOne);
			given(this.dashboardsAdminService.nameEquipment(7)).willReturn(listName);
			
			given(this.dashboardsAdminService.countEquipment(28)).willReturn(listOne);
			given(this.dashboardsAdminService.nameEquipment(28)).willReturn(listName);
			
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboard() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", true))
					.andExpect(model().attribute("hasEquipmentWeek", true))
					.andExpect(model().attribute("countMonth", COUNT))
					.andExpect(model().attribute("orderNameMonth", NAME))
					.andExpect(model().attribute("countWeek", COUNT))
					.andExpect(model().attribute("orderNameWeek", NAME));
		}
	}

	@Nested
	@DisplayName("Admin test without week")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutWeek {

		@MockBean
		private DashboardsAdminService dashboardService;
		
		@MockBean
		private ClientService clientService;
		
		@MockBean
		private InscriptionService inscriptionService;
		
		@MockBean
		private GuildService guildService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);
			List<String> listName = new ArrayList<>();
			listName.add("prueba");

			given(this.dashboardService.countEquipment(7)).willReturn(new ArrayList<>());
			given(this.dashboardService.nameEquipment(7)).willReturn(new ArrayList<>());
			
			given(this.dashboardService.countEquipment(28)).willReturn(listOne);
			given(this.dashboardService.nameEquipment(28)).willReturn(listName);
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitDashboardWithoutWeek() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", true))
					.andExpect(model().attribute("hasEquipmentWeek", false))
					.andExpect(model().attribute("countMonth", COUNT))
					.andExpect(model().attribute("orderNameMonth", NAME));
		}
	}

	@Nested
	@DisplayName("Admin test without trainings")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutTrainings {

		@MockBean
		private DashboardsAdminService dashboardService;
		
		@MockBean
		private ClientService clientService;
		
		@MockBean
		private InscriptionService inscriptionService;
		
		@MockBean
		private GuildService guildService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			given(this.dashboardService.countEquipment(7)).willReturn(new ArrayList<>());
			given(this.dashboardService.nameEquipment(7)).willReturn(new ArrayList<>());
			
			given(this.dashboardService.countEquipment(28)).willReturn(new ArrayList<>());
			given(this.dashboardService.nameEquipment(28)).willReturn(new ArrayList<>());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitDashboardWithoutWeek() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}
}
