package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.service.DashboardClientService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

public class DashboardClientControllerTests {

	private static final String TEST_USERNAME = "client1";

	private static final String TEST_USERNAME2 = "client2";

	private static final String TEST_USERNAME3 = "client3";

	private static final String TEST_USERNAME4 = "client4";

	private static final Integer[] COUNT_BODY_PART = { 1 };

	private static final String[] NAME_BODY_PART = { "BODY" };

	private static final Integer[] COUNT_REPETITION_TYPE = { 2 };

	private static final String[] NAME_REPETITION_TYPE = { "REPETITION" };

	private static final Integer SUM_KCAL = 1100;

	// Density data

	private static final Integer[] COUNT_BODY_PART2 = { 1, 1, 1, 1 };

	private static final String[] NAME_BODY_PART2 = { "BODY", "BODY", "BODY", "BODY" };

	private static final Integer[] COUNT_REPETITION_TYPE2 = { 2, 2, 2, 2 };

	private static final String[] NAME_REPETITION_TYPE2 = { "REPETITION", "REPETITION", "REPETITION", "REPETITION" };

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client1Tests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client2Tests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 2 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME2)).willReturn(null);
			// Client 2 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME2)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME2)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME2))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME2))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME2)).willReturn(1100);

		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitDashboardClientHisto() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME2)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", false))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", false))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", false))
					.andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}
	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client3Tests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {

			// Client 3 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME3)).willReturn(null);
			// Client 3 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME3)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME3)).willReturn(null);
		}

		@WithMockUser(username = TEST_USERNAME3, authorities = { "client" })
		@Test
		void testInitDashboardClientWithoutExercises() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME3)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", false))
					.andExpect(model().attribute("hasBodyPartsAll", false))
					.andExpect(model().attribute("hasRepetitionTypeMonth", false))
					.andExpect(model().attribute("hasRepetitionTypeAll", false))
					.andExpect(model().attribute("hasKcalMonth", false))
					.andExpect(model().attribute("hasKcalAll", false));
		}

		@WithMockUser(username = TEST_USERNAME3, authorities = { "client" })
		@Test
		void testWrongUsername() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("exception"));
		}
	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client4TestsWithoutBodyPartsMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 4 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME4)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME4)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME4))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME4))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME4)).willReturn(1100);
			// Client 4 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME4)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME4)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME4))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME4))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME4)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME4, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME4)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", false))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client5TestsWithoutRepetitionTypeMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", false))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client6TestsWithoutKcalMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(null);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", false))
					.andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client7TestsWithoutBodyPartsAll {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", false))
					.andExpect(model().attribute("hasBodyPartsAll", false))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client8TestsWithoutRepetitionTypeAll {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", false))
					.andExpect(model().attribute("hasRepetitionTypeAll", false))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client9TestsWithoutKcalAll {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(null);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(null);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", false))
					.andExpect(model().attribute("hasKcalAll", false))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client10TestsWithoutCountBodyPartsMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 4 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 4 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", false))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client11TestsWithoutNameBodyPartsMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 4 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 4 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", false))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client12TestsWithoutCountRepetitionTypeMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 4 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 4 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", false))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client13TestsWithoutNameRepetitionTypeMonth {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");

			// Client 4 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 4 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", false))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client14TestsWithDensityData {

		@MockBean
		private DashboardClientService dashboardClientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<Integer> listCountBodyPart = new ArrayList<Integer>();
			listCountBodyPart.add(1);
			listCountBodyPart.add(1);
			listCountBodyPart.add(1);
			listCountBodyPart.add(1);
			List<String> listNameBodyPart = new ArrayList<String>();
			listNameBodyPart.add("BODY");
			listNameBodyPart.add("BODY");
			listNameBodyPart.add("BODY");
			listNameBodyPart.add("BODY");
			List<Integer> listCountRepetitionType = new ArrayList<Integer>();
			listCountRepetitionType.add(2);
			listCountRepetitionType.add(2);
			listCountRepetitionType.add(2);
			listCountRepetitionType.add(2);
			List<String> listNameRepetitionType = new ArrayList<String>();
			listNameRepetitionType.add("REPETITION");
			listNameRepetitionType.add("REPETITION");
			listNameRepetitionType.add("REPETITION");
			listNameRepetitionType.add("REPETITION");

			// Client 1 28 days
			given(this.dashboardClientService.countBodyPart(28, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(28, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(28, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(28, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(28, TEST_USERNAME)).willReturn(1100);
			// Client 1 Historical
			given(this.dashboardClientService.countBodyPart(null, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(null, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(null, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(null, TEST_USERNAME))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(null, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyPartsMonth", true))
					.andExpect(model().attribute("hasBodyPartsAll", true))
					.andExpect(model().attribute("hasRepetitionTypeMonth", true))
					.andExpect(model().attribute("hasRepetitionTypeAll", true))
					.andExpect(model().attribute("hasKcalMonth", true)).andExpect(model().attribute("hasKcalAll", true))
					.andExpect(model().attribute("orderBodyPartsMonth", NAME_BODY_PART2))
					.andExpect(model().attribute("countBodyPartsMonth", COUNT_BODY_PART2))
					.andExpect(model().attribute("orderBodyPartsAll", NAME_BODY_PART2))
					.andExpect(model().attribute("countBodyPartsAll", COUNT_BODY_PART2))
					.andExpect(model().attribute("orderRepetitionTypeMonth", NAME_REPETITION_TYPE2))
					.andExpect(model().attribute("countRepetitionTypeMonth", COUNT_REPETITION_TYPE2))
					.andExpect(model().attribute("orderRepetitionTypeAll", NAME_REPETITION_TYPE2))
					.andExpect(model().attribute("countRepetitionTypeAll", COUNT_REPETITION_TYPE2))
					.andExpect(model().attribute("kcalMonth", SUM_KCAL))
					.andExpect(model().attribute("kcalAll", SUM_KCAL));
		}

	}

}
