package org.springframework.samples.yogogym.web.e2e;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class GuildControllerE2ETest {

	@Autowired
	GuildService guildService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

	}
	
	void testWrongAuth(int mode, String path, Object... uriVars) throws Exception {
		
		if (mode == 0) {
			mockMvc.perform(get(path, uriVars)).andExpect(status().isOk()).andExpect(view().name("exception"));
		} else {
			mockMvc.perform(post(path, uriVars)).andExpect(status().isForbidden());
		}
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testWrongClients() throws Exception {
		String clientUsername = "client1";
		
		int guilId = 2;
		
		testWrongAuth(0,"/client/{clientUsername}/guilds/{guildId}/edit",
				clientUsername, guilId);
		testWrongAuth(1,"/client/{clientUsername}/guilds/{guildId}/edit",
				clientUsername, guilId);
		testWrongAuth(0,
				"/client/{clientUsername}/guilds/{guildId}/delete",
				clientUsername, guilId);
		testWrongAuth(0,
				"/client/{clientUsername}/guilds/{guildId}/leave",
				clientUsername, guilId);
	}
	
	//GOOD TESTS
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testGetGuilds() throws Exception {
		String clientUsername = "client1";
		mockMvc.perform(get("/client/{clientUsername}/guilds", clientUsername)).andExpect(status().isOk())
		.andExpect(view().name("client/guilds/guildsList"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testGetGuildDetails() throws Exception{
		String clientUsername = "client1";
		Integer guildId = 1;
		
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}",
				clientUsername, guildId)).andExpect(status().isOk())
				.andExpect(view().name("client/guilds/guildsDetails")).andDo(print());
	}
	
	@WithMockUser(username = "client4", authorities = { "client" })
	@Test
	void testInitCreateGuildForm() throws Exception{
		String clientUsername = "client4";
		mockMvc.perform(get("/client/{clientUsername}/guilds/create",
			clientUsername)).andExpect(status().isOk())
			.andExpect(view().name("client/guilds/guildsCreateOrUpdate"))
			.andExpect(model().attributeExists("guild"));
	}
	
	@WithMockUser(username = "client4", authorities = { "client"})
	@Test
	void testProcessCreateGuildForm() throws Exception {
		
		String clientUsername = "client4";
		
		Guild guild = createGuild(clientUsername);
		mockMvc.perform(post("/client/{clientUsername}/guilds/create",clientUsername).with(csrf())
				.param("creator",guild.getCreator())
				.param("description", guild.getDescription())
				.param("logo",guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/" + clientUsername + "/guilds"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testInitUpdateGuildForm() throws Exception {
		
		String clientUsername = "client1";
		Integer guildId = 1;
		
		mockMvc.perform(
				get("/client/{clientUsername}/guilds/{guildId}/edit",
						clientUsername, guildId))
				.andExpect(status().isOk()).andExpect(view().name("client/guilds/guildsCreateOrUpdate"))
				.andExpect(model().attributeExists("guild"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessUpdateGuildForm() throws Exception {
		
		String clientUsername = "client1";
		Integer guildId = 1;
		Guild guild = createGuild(clientUsername);
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/edit",clientUsername, guildId).with(csrf())
				.param("creator", clientUsername)
				.param("description", guild.getDescription())
				.param("logo", guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/" + clientUsername + "/guilds/" + guildId));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testDeleteGuild() throws Exception {
		String clientUsername = "client1";
		Integer guildId = 1;
		
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/delete",clientUsername, guildId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/client/{clientUsername}/guilds"));
		
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testJoinGuild() throws Exception {
		String clientUsername = "client1";
		Integer guildId = 2;
		
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/join",clientUsername, guildId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/client/{clientUsername}/guilds"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testLeaveGuild() throws Exception {
		String clientUsername = "client1";
		Integer guildId = 1;
		
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/leave",clientUsername, guildId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/client/{clientUsername}/guilds"));
	}
	
	//BAD TESTS
	
	//CREATE
	@WithMockUser(username = "client4", authorities = { "client"})
	@Test
	void testProcessErrorCreateGuildEmptyForm() throws Exception {
		
		String clientUsername = "client4";
		
		Guild guild = createGuild(clientUsername);
		guild.setDescription("");
		guild.setLogo("");
		guild.setName("");
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/create",clientUsername).with(csrf())
				.param("creator",guild.getCreator())
				.param("description", guild.getDescription())
				.param("logo",guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	
	@WithMockUser(username = "client4", authorities = { "client"})
	@Test
	void testProcessErrorCreateGuildSameNameForm() throws Exception {
		
		String clientUsername = "client4";
		
		Guild guild = createGuild(clientUsername);
		guild.setName("Calisthenics");
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/create",clientUsername).with(csrf())
				.param("creator",guild.getCreator())
				.param("description", guild.getDescription())
				.param("logo",guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	@WithMockUser(username = "client4", authorities = { "client"})
	@Test
	void testProcessErrorCreateGuildBadURLForm() throws Exception {
		
		String clientUsername = "client4";
		
		Guild guild = createGuild(clientUsername);
		guild.setLogo("badurl.png");
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/create",clientUsername).with(csrf())
				.param("creator",guild.getCreator())
				.param("description", guild.getDescription())
				.param("logo",guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client"})
	@Test
	void testProcessErrorCreateGuildSameCreatorForm() throws Exception {
		
		String clientUsername = "client1";
		
		Guild guild = createGuild(clientUsername);
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/create",clientUsername).with(csrf())
				.param("creator",guild.getCreator())
				.param("description", guild.getDescription())
				.param("logo",guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	//UPDATE
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessErrorUpdateGuildEmptyForm() throws Exception {
		
		String clientUsername = "client1";
		Integer guildId = 1;
		Guild guild = createGuild(clientUsername);
		guild.setName("");
		guild.setDescription("");
		guild.setLogo("");
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/edit",clientUsername, guildId).with(csrf())
				.param("creator", clientUsername)
				.param("description", guild.getDescription())
				.param("logo", guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	@WithMockUser(username = "client2", authorities = { "client" })
	@Test
	void testProcessErrorUpdateGuildSameNameForm() throws Exception {
		
		String clientUsername = "client2";
		Integer guildId = 2;
		Guild guild = createGuild(clientUsername);
		guild.setName("Calisthenics");
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/edit",clientUsername, guildId).with(csrf())
				.param("creator", clientUsername)
				.param("description", guild.getDescription())
				.param("logo", guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessErrorUpdateGuildBadURLForm() throws Exception {
		
		String clientUsername = "client1";
		Integer guildId = 1;
		Guild guild = createGuild(clientUsername);
		guild.setLogo("badurl.jpg");
		
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/edit",clientUsername, guildId).with(csrf())
				.param("creator", clientUsername)
				.param("description", guild.getDescription())
				.param("logo", guild.getLogo())
				.param("name",guild.getName()))
				.andExpect(view().name("client/guilds/guildsCreateOrUpdate"));
	}
	
	//Derivated Methods
	protected Guild createGuild(String clientUsername) {
		
		Guild guild = new Guild();
		guild.setCreator(clientUsername);
		guild.setDescription("This is an example");
		guild.setLogo("https://foto_ejemplo.png");
		guild.setName("Guild example2");
		
		return guild;
	}
	
}
