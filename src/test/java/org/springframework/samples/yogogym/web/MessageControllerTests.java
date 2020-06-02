package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Forum;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ForumService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.MessageService;
import org.springframework.context.annotation.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = MessageController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class MessageControllerTests {
	
	private static final String ERROR_STRING = "257Charactersimctuhhrmkhdppltsxfsxcoqdelbrauxsxmjqnbahtncugcaogqeyrbmt"
		+ "pwquyzrpruwuxnivgvxlympvcwkevqsrhwwehkbtsgnwzidmlhnjibotvovjbtomuuulonmgursijdfmlrsminpwolwjhpkjacmveyaykcw"
		+ "tsjiocycaqaaafanjvedcysqjrcbprlozbljqenxnugkcmbbcyejuhzuidyyhevmmeiyxqlbvcuptywc";
	
	private static final int GUILD1_ID = 1;
	private static final int GUILD2_ID = 2;
	
	private static final int FORUM1_ID = 1;
	private static final int FORUM2_ID = 2;
	
	private static final int MESSAGE1_ID = 1;
	private static final int MESSAGE2_ID = 2;
	private static final int MESSAGE3_ID = 3;
	
	private static final String CLIENT1_USERNAME = "client1";
	private static final String CLIENT2_USERNAME = "client2";
	private static final String CLIENT3_USERNAME = "client3";
	
	private static final int CLIENT1_ID = 1;
	private static final int CLIENT2_ID = 2;
	private static final int CLIENT3_ID = 3;
	
	private static final String NIF1 = "12345678F";
	private static final String NIF2 = "12345678G";
	private static final String NIF3 = "12345678H";
	
	
	@MockBean
	private MessageService messageService;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private GuildService guildService;
	
	@MockBean
	private ForumService forumService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setup() {
		
		Guild guild1 = new Guild();
		guild1.setId(GUILD1_ID);
		guild1.setCreator(CLIENT1_USERNAME);
		guild1.setDescription("We are connecting the world");
		guild1.setName("Connecting");
		guild1.setLogo("https://omega2001.es/wp-content/uploads/2016/02/red-informatica-1080x675.jpg");
		
		Forum forum1 = new Forum();
		forum1.setId(FORUM1_ID);
		forum1.setGuild(guild1);
		
		Guild guild2 = new Guild();
		guild2.setId(GUILD2_ID);
		guild2.setCreator(CLIENT2_USERNAME);
		guild2.setDescription("We are tryng to connect the world");
		guild2.setName("Connecting Too");
		guild2.setLogo("https://omega2001.es/wp-content/uploads/2016/02/red-informatica-1080x675.jpg");
		
		Forum forum2 = new Forum();
		forum2.setId(FORUM2_ID);
		forum2.setGuild(guild2);
		
		Client client1 = new Client();
		User userClient1 = new User();
		userClient1.setUsername(CLIENT1_USERNAME);
		userClient1.setEnabled(true);
		client1.setUser(userClient1);
		client1.setId(CLIENT1_ID);
		client1.setNif(NIF1);
		client1.setIsPublic(true);
		client1.setGuild(guild1);
		
		Client client2 = new Client();
		User userClient2 = new User();
		userClient2.setUsername(CLIENT2_USERNAME);
		userClient2.setEnabled(true);
		client2.setUser(userClient2);
		client2.setId(CLIENT2_ID);
		client2.setNif(NIF2);
		client2.setIsPublic(true);
		client2.setGuild(guild2);
		
		Client client3 = new Client();
		User userClient3 = new User();
		userClient3.setUsername(CLIENT3_USERNAME);
		userClient3.setEnabled(true);
		client3.setUser(userClient3);
		client3.setId(CLIENT3_ID);
		client3.setNif(NIF3);
		client3.setIsPublic(true);
		client3.setGuild(guild1);
		
		Message message1 = new Message();
		message1.setId(MESSAGE1_ID);
		message1.setUser(userClient1);
		message1.setIsParent(true);
		message1.setEdited(false);
		message1.setContent("Test message 1");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		message1.setCreatedAt(cal.getTime());
		
		Message message2 = new Message();
		message2.setId(MESSAGE2_ID);
		message2.setUser(userClient3);
		message2.setIsParent(false);
		message2.setEdited(false);
		message2.setContent("Test message 2");
		message2.setAnswers(new ArrayList<>());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		message2.setCreatedAt(cal.getTime());
		
		Collection<Message> answersMessage1 = new ArrayList<>();
		answersMessage1.add(message2);
		message1.setAnswers(answersMessage1);
		
		Collection<Message> messagesForum1 = new ArrayList<>();
		messagesForum1.add(message1);
		messagesForum1.add(message2);
		forum1.setMessages(messagesForum1);
		
		Message message3 = new Message();
		message3.setId(MESSAGE3_ID);
		message3.setUser(userClient2);
		message3.setIsParent(true);
		message3.setEdited(false);
		message3.setContent("Test message 3");
		message3.setAnswers(new ArrayList<>());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		message3.setCreatedAt(cal.getTime());
		
		Collection<Message> messagesForum2 = new ArrayList<>();
		messagesForum2.add(message3);
		forum2.setMessages(messagesForum2);
		
		given(this.clientService.findClientByUsername(CLIENT1_USERNAME)).willReturn(client1);
		given(this.clientService.findClientByUsername(CLIENT2_USERNAME)).willReturn(client2);
		given(this.clientService.findClientByUsername(CLIENT3_USERNAME)).willReturn(client3);
		given(this.guildService.findGuildById(GUILD1_ID)).willReturn(guild1);
		given(this.guildService.findGuildById(GUILD2_ID)).willReturn(guild2);
		given(this.forumService.findForumByGuildId(GUILD1_ID)).willReturn(forum1);
		given(this.forumService.findForumByGuildId(GUILD2_ID)).willReturn(forum2);
		given(this.messageService.findAllForumParentMessages(FORUM1_ID)).willReturn(messagesForum1);
		given(this.messageService.findAllForumParentMessages(FORUM2_ID)).willReturn(messagesForum2);
		given(this.messageService.findMessageFromId(MESSAGE1_ID)).willReturn(message1);
		given(this.messageService.findMessageFromId(MESSAGE2_ID)).willReturn(message2);
		given(this.messageService.findMessageFromId(MESSAGE3_ID)).willReturn(message3);
		
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testGuildForumWrongUsername() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testGuildForumWrongGuild() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}",CLIENT2_USERNAME,GUILD1_ID,FORUM1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testGuildForumWrongForum() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}",CLIENT2_USERNAME,GUILD2_ID,FORUM1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testCreateMessageWrongUsername() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testCreateMessageWrongGuild() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create",CLIENT2_USERNAME,GUILD1_ID,FORUM1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testCreateMessageWrongForum() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create",CLIENT2_USERNAME,GUILD2_ID,FORUM1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testCreateAnswerWrongUsername() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testCreateAnswerWrongGuild() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT2_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testCreateAnswerWrongForum() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT2_USERNAME,GUILD2_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testCreateAnswerMessageNotFound() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE3_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testCreateAnswerMessageNotParent() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE2_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testGuildForum() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("guild"))
			.andExpect(model().attribute("guild", hasProperty("creator", is(CLIENT1_USERNAME))))
			.andExpect(model().attribute("guild", hasProperty("description", is("We are connecting the world"))))
			.andExpect(model().attribute("guild", hasProperty("name", is("Connecting"))))
			.andExpect(model().attribute("guild", hasProperty("logo", is("https://omega2001.es/wp-content/uploads/2016/02/red-informatica-1080x675.jpg"))))
			.andExpect(model().attributeExists("messages"))
			.andExpect(model().attribute("messages", hasItem(allOf(hasProperty("id", is(MESSAGE1_ID)),hasProperty("isParent", is(true)),
				hasProperty("edited", is(false)),hasProperty("content", is("Test message 1")),
				hasProperty("answers", hasItem(allOf(hasProperty("id", is(MESSAGE2_ID)),hasProperty("isParent", is(false)),
					hasProperty("edited", is(false)),hasProperty("content", is("Test message 2")),hasProperty("answers", empty()))))))))
			.andExpect(view().name("client/forums/messagesList"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testprocessCreateMessageSuccess() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID)
			.with(csrf())
			.param("content", "New message"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/client/" + CLIENT1_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testInitCreateMessage() throws Exception {
		mockMvc.perform(get("/client/forums/messagesCreateForm"))
			.andExpect(status().isOk())
	 		.andExpect(model().attributeExists("message"))
			.andExpect(view().name("client/forums/messagesCreateForm"));
	}

	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"", ERROR_STRING})
	void testprocessCreateMessageHasErrorsContentNotValid(String content) throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID)
			.with(csrf())
			.param("content", content))
			.andExpect(status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.flash().attribute("wrongMessage", "The message wasn't valid"))
			.andExpect(view().name("redirect:/client/" + CLIENT1_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testInitCreateAnswer() throws Exception {
		mockMvc.perform(get("/client/forums/messagesAnswerForm"))
			.andExpect(status().isOk())
	 		.andExpect(model().attributeExists("answer"))
			.andExpect(view().name("client/forums/messagesAnswerForm"));
	}
	
	@WithMockUser(username=CLIENT3_USERNAME, authorities= {"client"})
	@Test
	void testprocessCreateAnswerSuccess() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT3_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf())
			.param("content", "New answer"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/client/" + CLIENT3_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
	}
	
	@WithMockUser(username=CLIENT3_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"", ERROR_STRING})
	void testprocessCreateAnswerHasErrorsContentNotValid(String content) throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT3_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf())
			.param("content", content))
			.andExpect(status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.flash().attribute("wrongMessage", "The message wasn't valid"))
			.andExpect(view().name("redirect:/client/" + CLIENT3_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
	}
}