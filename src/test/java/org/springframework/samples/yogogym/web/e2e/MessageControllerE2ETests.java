package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.samples.yogogym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class MessageControllerE2ETests {
	
	private static final String ERROR_STRING = "257Charactersimctuhhrmkhdppltsxfsxcoqdelbrauxsxmjqnbahtncugcaogqeyrbmt"
		+ "pwquyzrpruwuxnivgvxlympvcwkevqsrhwwehkbtsgnwzidmlhnjibotvovjbtomuuulonmgursijdfmlrsminpwolwjhpkjacmveyaykcw"
		+ "tsjiocycaqaaafanjvedcysqjrcbprlozbljqenxnugkcmbbcyejuhzuidyyhevmmeiyxqlbvcuptywc";
	
	private static final int GUILD1_ID = 1;
	private static final int GUILD2_ID = 2;
	
	private static final int FORUM1_ID = 1;
	
	//Messages
	private static final int MESSAGE1_ID = 1;
	private static final int MESSAGE7_ID = 7;
	
	//Answers
	private static final int M1_MESSAGE4_ID = 4;
	private static final int M1_MESSAGE5_ID = 5;
	
	private static final String CLIENT1_USERNAME = "client1";
	private static final String CLIENT2_USERNAME = "client2";
	private static final String CLIENT5_USERNAME = "client5";
	
	@Autowired
	MessageService messageService;

	@Autowired
	private MockMvc mockMvc;
	
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
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE7_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testCreateAnswerMessageNotParent() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID,M1_MESSAGE4_ID)
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
			.andExpect(model().attribute("guild", hasProperty("description", is("Here we practice Calisthenics for everyone"))))
			.andExpect(model().attribute("guild", hasProperty("name", is("Calisthenics"))))
			.andExpect(model().attribute("guild", hasProperty("logo", is("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/heria10-1566732385.jpg?crop=1.00xw:0.922xh;0,0.0778xh&resize=980:*"))))
			.andExpect(model().attributeExists("messages"))
			.andExpect(model().attribute("messages", hasSize(3)))
			.andExpect(model().attribute("messages", hasItem(allOf(hasProperty("id", is(MESSAGE1_ID)),hasProperty("isParent", is(true)),
				hasProperty("edited", is(false)),hasProperty("content", is("Mensaje de prueba")),
				hasProperty("answers", hasItem(allOf(hasProperty("id", is(M1_MESSAGE4_ID)),hasProperty("isParent", is(false)),
					hasProperty("edited", is(false)),hasProperty("content", is("Respuesta de prueba")),hasProperty("answers", empty())))),
				hasProperty("answers", hasItem(allOf(hasProperty("id", is(M1_MESSAGE5_ID)),hasProperty("isParent", is(false)),
					hasProperty("edited", is(false)),hasProperty("content", is("Respuesta de prueba 2")),hasProperty("answers", empty()))))))))
			.andExpect(view().name("client/forums/messagesList"));
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
	@Test
	void testprocessCreateMessageSuccess() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create",CLIENT1_USERNAME,GUILD1_ID,FORUM1_ID)
			.with(csrf())
			.param("content", "New message"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/client/" + CLIENT1_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
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

	@WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
	@Test
	void testprocessCreateAnswerSuccess() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT5_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf())
			.param("content", "New answer"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/client/" + CLIENT5_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
	}
	
	@WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"", ERROR_STRING})
	void testprocessCreateAnswerHasErrorsContentNotValid(String content) throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}",CLIENT5_USERNAME,GUILD1_ID,FORUM1_ID,MESSAGE1_ID)
			.with(csrf())
			.param("content", content))
			.andExpect(status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.flash().attribute("wrongMessage", "The message wasn't valid"))
			.andExpect(view().name("redirect:/client/" + CLIENT5_USERNAME + "/guilds/" + GUILD1_ID + "/forums/" + FORUM1_ID));
	}
}