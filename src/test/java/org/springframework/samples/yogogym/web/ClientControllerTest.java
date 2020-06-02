package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ClientController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ClientControllerTest {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String CLIENT1_USERNAME = "client1";
	private static final String CLIENT2_USERNAME = "client2";
	private static final String CLIENT3_USERNAME = "client3";
	private static final String CLIENT1_NIF = "12345678A";
	private static final String CLIENT2_NIF = "12345678B";
	private static final String CLIENT3_NIF = "12345678C";
	private static final int CLIENT1_ID = 1;
	private static final int CLIENT3_ID = 3;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private TrainerService trainerService;
	
	@Autowired
	private MockMvc mockMvc;
		
	
	@BeforeEach
	void setUp()
	{	
		String[] usernames = {CLIENT1_USERNAME, CLIENT2_USERNAME};
		String[] nifs = {CLIENT1_NIF, CLIENT2_NIF};
		List<Client> clients = createClients(usernames, nifs);
		
		Trainer trainer1 = createTrainer(TRAINER1_USERNAME, clients);
		
		Client client3 = createClient(CLIENT3_USERNAME, CLIENT3_NIF, CLIENT3_ID);
		
		given(this.trainerService.findTrainer(TRAINER1_USERNAME)).willReturn(trainer1);
		given(this.clientService.findClientById(CLIENT1_ID)).willReturn(clients.get(CLIENT1_ID - 1));
		given(this.clientService.findClientById(CLIENT3_ID)).willReturn(client3);
	}

	@WithMockUser(value = "trainer1", authorities = {"trainer"})
	@Test
	void testFindMyClientsTrainer() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients", TRAINER1_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("trainer"))
		.andExpect(view().name("trainer/clients/clientsList"));
	}
	
	@WithMockUser(value = "trainer1", authorities = {"trainer"})
	@Test
	void testShowMyClientTrainer() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}", TRAINER1_USERNAME, CLIENT1_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("client"))
		.andExpect(view().name("trainer/clients/clientsDetails"));
	}
	
	@WithMockUser(value = "trainer1", authorities = {"trainer"})
	@Test
	void testShouldNotShowNotMyClientTrainer() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}", TRAINER1_USERNAME, CLIENT3_ID)).andExpect(status().isOk())
		.andExpect(model().attributeDoesNotExist("client"))
		.andExpect(view().name("exception"));
	}
	
	
	
	// UTILS
	
	private List<Client> createClients(String[] usernames, String[] nifs) {
		
		List<Client> res = new ArrayList<Client>();
		
		for(int i = 0; i < usernames.length; i++) {
			
			Client client = new Client();
			User userClient = new User();
			userClient.setUsername(usernames[i]);
			userClient.setEnabled(true);
			client.setUser(userClient);
			client.setNif(nifs[i]);
			client.setIsPublic(true);
			client.setId(i+1);
			
			res.add(client);
		}
		return res;
	}
	
	private Client createClient(String username, String nif, int id) {
		
		Client client = new Client();
		User userClient = new User();
		userClient.setUsername(username);
		userClient.setEnabled(true);
		client.setUser(userClient);
		client.setNif(nif);
		client.setIsPublic(true);
		client.setId(id);
		
		return client;
	}

	private Trainer createTrainer(String username, Collection<Client> clients) {
		
		Trainer res = new Trainer();
		
		res.setEmail(username + "@email.com");
		User userTrainer = new User();
		userTrainer.setUsername(TRAINER1_USERNAME);
		userTrainer.setEnabled(true);
		res.setUser(userTrainer);
		res.setClients(clients);
		
		return res;
	}
	
}
