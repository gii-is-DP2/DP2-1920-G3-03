package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageClients;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageGuilds;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;
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
	
	private static final String[] challengesNames = { "Challenge1 Name Test" };
	private static final long[] percentageClients = { 100 };
	private static final long[] percentageGuilds = { 100 };

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
	
	@Nested
	@DisplayName("Admin Dashboard Challenges Test All Ok")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminDashboardChallengesTestOk {

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
			
			String[] challengesNames = {"Challenge1 Name Test"};
			
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			
			given(this.dashboardsAdminService.countChallengesOfMonthAndYear(month,year)).willReturn(1);
			
			given(this.dashboardsAdminService.countCompletedInscriptionsOfMonthAndYear(month,year)).willReturn(1);
			
			given(this.dashboardsAdminService.getChallengesNamesOfMonthAndYear(month,year)).willReturn(challengesNames);
			
			DashboardAdminChallengesTopClient topClient = new DashboardAdminChallengesTopClient() {
				
				public String getUsername() {
					return "username";
				}
				
				public Integer getPoints() {
					return 100;
				}
				
				public String getEmail() {
					return "username@yogogym.com";
				}
			};
			given(this.dashboardsAdminService.getTopClient(month,year)).willReturn(topClient);
			
			DashboardAdminChallengesTopGuild topGuild = new DashboardAdminChallengesTopGuild() {
				
				public Integer getPoints() {
					return 100;
				}
				
				public String getGuild() {
					return "Connecting";
				}
			};
			
			given(this.dashboardsAdminService.getTopGuild(month,year)).willReturn(topGuild);
			
			DashboardAdminChallengesPercentageClients percentageClient = new DashboardAdminChallengesPercentageClients() {
				
				public Double getPercentageClients() {
					return 1.;
				}
				
				public String getChallengeName() {
					return challengesNames[0];
				}
			};
			
			List<DashboardAdminChallengesPercentageClients> percentagesClients = new ArrayList<>();
			percentagesClients.add(percentageClient);
			
			given(this.dashboardsAdminService.getPercentageClients(month,year)).willReturn(percentagesClients);
			
			DashboardAdminChallengesPercentageGuilds percentageGuild = new DashboardAdminChallengesPercentageGuilds() {
				
				public String getChallengeName() {
					return challengesNames[0];
				}

				public Double getPercentageGuilds() {
					return 1.;
				}
			};
			
			List<DashboardAdminChallengesPercentageGuilds> percentagesGuilds = new ArrayList<>();
			percentagesGuilds.add(percentageGuild);
			
			given(this.dashboardsAdminService.getPercentageGuilds(month,year)).willReturn(percentagesGuilds);
			
			
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboardChallenges() throws Exception {
			mockMvc.perform(get("/admin/dashboardChallenges")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardChallenges"))
					.andExpect(model().attribute("ChallengesExists", true))
					.andExpect(model().attribute("client", "username"))
					.andExpect(model().attribute("email", "username@yogogym.com"))
					.andExpect(model().attribute("cPoints", 100))
					.andExpect(model().attribute("guild", "Connecting"))
					.andExpect(model().attribute("gPoints", 100))
					.andExpect(model().attribute("challengesNames", challengesNames))
					.andExpect(model().attribute("percentageClients", percentageClients))
					.andExpect(model().attribute("percentageGuilds", percentageGuilds));
		}	
	}
	
	@Nested
	@DisplayName("Admin Dashboard Challenges Test No Challenges")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminDashboardChallengesTestNoChallenges{

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
			
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			
			given(this.dashboardsAdminService.countChallengesOfMonthAndYear(month,year)).willReturn(0);
			
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboardChallenges() throws Exception {
			mockMvc.perform(get("/admin/dashboardChallenges")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardChallenges"))
					.andExpect(model().attribute("ChallengesExists", false));
		}	
	}
	
	@Nested
	@DisplayName("Admin Dashboard Challenges Test No Completed Challenges")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminDashboardChallengesTestNoCompletedChallenges {

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
			
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			
			given(this.dashboardsAdminService.countChallengesOfMonthAndYear(month,year)).willReturn(1);
			
			given(this.dashboardsAdminService.countCompletedInscriptionsOfMonthAndYear(month,year)).willReturn(0);
			
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboardChallenges() throws Exception {
			mockMvc.perform(get("/admin/dashboardChallenges")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardChallenges"))
					.andExpect(model().attribute("ChallengesExists", true))
					.andExpect(model().attribute("NoCompletedChallenges", true));
		}	
	}
	
	@Nested
	@DisplayName("Admin Dashboard General Test Ok")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminDashboardGeneralTestOk {

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
			
			//client 1:
			Client client1 = new Client();
			User userClient1 = new User();
			userClient1.setUsername("username");
			userClient1.setEnabled(true);
			client1.setUser(userClient1);
			client1.setId(1);

			//trainer 1:
			Trainer trainer1 = new Trainer();
			User userTrainer1 = new User();
			trainer1.setUser(userTrainer1);
			trainer1.setId(2);

			//Guild 1:
			Guild guild1 = new Guild();
			guild1.setId(1);
			guild1.setCreator("username");
			guild1.setDescription("We are connecting the world");
			guild1.setName("Connecting");
			guild1.setLogo("https://omega2001.es/wp-content/uploads/2016/02/red-informatica-1080x675.jpg");
			client1.setGuild(guild1);			
			
			List<Client> listClients = new ArrayList<Client>();
			listClients.add(client1);
			given(this.dashboardsAdminService.countClients()).willReturn(listClients.size());
			
			List<Trainer> listTrainers = new ArrayList<Trainer>();
			listTrainers.add(trainer1);
			given(this.dashboardsAdminService.countTrainers()).willReturn(listTrainers.size());

			
			List<Integer> countClientsPerGuild = new ArrayList<>();
			countClientsPerGuild.add(1);
			given(this.dashboardsAdminService.countClientsPerGuild()).willReturn(countClientsPerGuild);
			
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboardGeneral() throws Exception {
			mockMvc.perform(get("/admin/dashboardGeneral")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardGeneral"))
					.andExpect(model().attribute("dataExists", true))
					.andExpect(model().attributeExists("clients"))
					.andExpect(model().attributeExists("trainers"))
					.andExpect(model().attribute("clients", 1))
					.andExpect(model().attribute("trainers", 1));
		}	
	}
	
	@Nested
	@DisplayName("Admin Dashboard General Test No Information")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminDashboardGeneralTestNoInformation{

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

			
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboardChallenges() throws Exception {
			mockMvc.perform(get("/admin/dashboardGeneral")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardGeneral"))
					.andExpect(model().attribute("dataExists", false));
		}	
	}
}
