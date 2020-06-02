package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
			Integer[] listCountBodyPart = {1};
			String[] listNameBodyPart = {"BODY"};
			Integer[] listCountRepetitionType = {2};
			String[] listNameRepetitionType = {"REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", true))
					.andExpect(model().attribute("hasRepetitionType", true))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			
			Integer[] count = {};
			
			String[] name = {};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME2)).willReturn(count);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME2)).willReturn(name);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME2)).willReturn(count);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME2)).willReturn(name);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME2)).willReturn(null);

		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitDashboardClientEmpty() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME2)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", false))
					.andExpect(model().attribute("hasRepetitionType", false))
					.andExpect(model().attribute("hasKcal", false));
		}
		
		@WithMockUser(username = TEST_USERNAME3, authorities = { "client" })
		@Test
		void testWrongUsername() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
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
			
			Integer[] count = {};
			String[] name = {};
			
			Integer[] listCountRepetitionType = {2};
			String[] listNameRepetitionType = {"REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME4)).willReturn(count);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME4)).willReturn(name);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME4))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME4))
					.willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME4)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME4, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME4)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", false))
					.andExpect(model().attribute("hasRepetitionType", true))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			
			Integer[] count = {};
			String[] name = {};
			
			Integer[] listCountBodyPart = {1};
			String[] listNameBodyPart = {"BODY"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME)).willReturn(count);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(name);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", true))
					.andExpect(model().attribute("hasRepetitionType", false))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			Integer[] listCountBodyPart = {1};
			String[] listNameBodyPart = {"BODY"};
			Integer[] listCountRepetitionType = {2};
			String[] listNameRepetitionType = {"REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(null);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", true))
					.andExpect(model().attribute("hasRepetitionType", true))
					.andExpect(model().attribute("hasKcal", false))
					.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART))
					.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE));
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
			Integer[] listCountBodyPart = {};
			String[] listNameBodyPart = {"BODY"};
			Integer[] listCountRepetitionType = {2};
			String[] listNameRepetitionType = {"REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", false))
					.andExpect(model().attribute("hasRepetitionType", true))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			Integer[] listCountBodyPart = {1};
			String[] listNameBodyPart = {};
			Integer[] listCountRepetitionType = {2};
			String[] listNameRepetitionType = {"REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", false))
					.andExpect(model().attribute("hasRepetitionType", true))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE))
					.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			Integer[] listCountBodyPart = {1};
			String[] listNameBodyPart = {"BODY"};
			Integer[] listCountRepetitionType = {};
			String[] listNameRepetitionType = {"REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", true))
					.andExpect(model().attribute("hasRepetitionType", false))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			Integer[] listCountBodyPart = {1};
			String[] listNameBodyPart = {"BODY"};
			Integer[] listCountRepetitionType = {2};
			String[] listNameRepetitionType = {};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", true))
					.andExpect(model().attribute("hasRepetitionType", false))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART))
					.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART))
					.andExpect(model().attribute("kcal", SUM_KCAL));
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
			Integer[] listCountBodyPart = {1, 1, 1, 1};
			String[] listNameBodyPart = {"BODY", "BODY", "BODY", "BODY"};
			Integer[] listCountRepetitionType = {2, 2, 2, 2};
			String[] listNameRepetitionType = {"REPETITION", "REPETITION", "REPETITION", "REPETITION"};

			given(this.dashboardClientService.countBodyPart(5, 2020, TEST_USERNAME)).willReturn(listCountBodyPart);
			given(this.dashboardClientService.nameBodyPart(5, 2020, TEST_USERNAME)).willReturn(listNameBodyPart);
			given(this.dashboardClientService.countRepetitionType(5, 2020, TEST_USERNAME))
					.willReturn(listCountRepetitionType);
			given(this.dashboardClientService.nameRepetitionType(5, 2020, TEST_USERNAME)).willReturn(listNameRepetitionType);
			given(this.dashboardClientService.sumKcal(5, 2020, TEST_USERNAME)).willReturn(1100);
			
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-05", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasBodyParts", true))
					.andExpect(model().attribute("hasRepetitionType", true))
					.andExpect(model().attribute("hasKcal", true))
					.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART2))
					.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART2))
					.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE2))
					.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE2))
					.andExpect(model().attribute("kcal", SUM_KCAL));
		}

	}

}
