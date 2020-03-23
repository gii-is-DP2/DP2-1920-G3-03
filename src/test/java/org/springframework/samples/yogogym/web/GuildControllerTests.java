package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = GuildController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class GuildControllerTests {
	
	private static final int testGuildId1 = 1;
	private static final int testGuildId2 = 2;
	private static final int testClientId1 = 1;
	private static final String testClientUsername1 = "client1";
	
	@MockBean
	private GuildService guildService;
	
	@MockBean
	private ClientService clientService;
	
	@Autowired
	private MockMvc mockMvc;
	


}
