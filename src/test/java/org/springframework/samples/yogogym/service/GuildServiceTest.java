package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Forum;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.exceptions.GuildLogoException;
import org.springframework.samples.yogogym.service.exceptions.GuildNotCreatorException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameCreatorException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameNameException;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GuildServiceTest {

	@Autowired
	protected GuildService guildService;
	@Autowired
	protected ClientService clientService;
	@Autowired
	protected ForumService forumService;
	
	
	@Test
	void shouldFindAllGuilds(){
		Collection<Guild> guilds = (Collection<Guild>) this.guildService.findAllGuild();
		assertThat(guilds.size()).isEqualTo(3);
	}
	
	@Test
	void shouldFindGuildById(){
		Guild guild = this.guildService.findGuildById(1);
		assertThat(guild.getCreator()).isEqualTo("client1");	
	}
	
	@Test
	void shouldFindClientsByGuild() {
		Guild guild = this.guildService.findGuildById(1);
		Collection<Client> clients = this.guildService.findAllClientesByGuild(guild);
		assertThat(clients.size()).isEqualTo(2);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldSaveGuild() {
		
		Collection<Forum> forums = this.forumService.findAllForums();
		int foundForums = forums.size();
		
		Guild guild = createGuildTesting();
		Client c = createClientTesting(guild.getCreator());
		try {
			this.guildService.saveGuild(guild,c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		guild = this.guildService.findGuildById(guild.getId());
		assertThat(guild.getCreator().equals("CarlosD"));
		assertThat(guild.getName().equals("Programming 4ever"));
		assertThat(guild.getDescription().equals("Best Programming Guild"));
		assertThat(guild.getLogo().equals("https://i.blogs.es/fd396a/hook/450_1000.jpg"));
		
		Collection<Forum> allForums = this.forumService.findAllForums();
		assertThat(allForums.size()).isEqualTo(foundForums+1);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldNotSaveGuildWithSameName() {
		
		Guild g1 = createGuildTesting();
		Guild g2 = createGuildTesting();
		Client c = createClientTesting(g1.getCreator());
		g1.setName("Name");
		g2.setName("Name");
		
		try{
		this.guildService.saveGuild(g1,c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Assertions.assertThrows(GuildSameNameException.class, () ->{
			this.guildService.saveGuild(g2,c);
		});	
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldNotSaveGuildWithSameCreator() {
		
		Guild g1 = createGuildTesting();
		Guild g2 = createGuildTesting();
		Client c = createClientTesting(g1.getCreator());
		g1.setCreator("CarlosD");
		g2.setCreator("CarlosD");
		g2.setName("GymPrueba");
		
		try{
			this.guildService.saveGuild(g1,c);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Assertions.assertThrows(GuildSameCreatorException.class, () ->{
				this.guildService.saveGuild(g2,c);
			});	
	}
	
	@Test
	void shouldNotSaveGuildWithoutBeingTheCreator(){
		Guild g1 = createGuildTesting();
		Client c1 = createClientTesting("not same client");
		Assertions.assertThrows(GuildNotCreatorException.class, () ->{
			this.guildService.saveGuild(g1, c1);
		});
	}
	
	@Test
	void shouldNotSaveGuildWithBadURL() {
		
	
		Guild g1 = createGuildTesting();
		Client c1 = createClientTesting(g1.getCreator());
		g1.setLogo("EstaUrlEstaMal.com");
				
		Assertions.assertThrows(GuildLogoException.class, () ->{
			this.guildService.saveGuild(g1,c1);
		});
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldDeleteGuild() {
		
		Collection<Forum> forums = this.forumService.findAllForums();
		int foundForums = forums.size();
		
		Collection<Guild> guilds = this.guildService.findAllGuild();
		int foundBefore = guilds.size();
		Guild guild = this.guildService.findGuildById(2);
		try {
			this.guildService.deleteGuild(guild,"client2");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		guilds = this.guildService.findAllGuild();
		int foundAfter = guilds.size();
		
		assertThat(foundBefore).isGreaterThan(foundAfter);
		
		Collection<Forum> allForums = this.forumService.findAllForums();
		assertThat(allForums.size()).isEqualTo(foundForums-1);
	}
	
	@Test 
	void shouldNotDeleteGuild() {
		
		Guild guild = this.guildService.findGuildById(2);
		Client c = this.clientService.findClientById(4);
		Collection <Guild> guilds = this.guildService.findAllGuild();
		int foundBefore = guilds.size();
		this.guildService.deleteGuild(guild,c.getUser().getUsername());
		guilds = this.guildService.findAllGuild();
		int foundAfter = guilds.size();
		
		assertThat(foundBefore).isEqualTo(foundAfter);	
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldJoinGuild() {
		
		Client c = this.clientService.findClientById(6);
		Guild g = this.guildService.findGuildById(1);
		this.guildService.joinGuild(c.getUser().getUsername(), g);
		assertTrue(c.getGuild().equals(g));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldLeaveGuild() {
		
		Client c = this.clientService.findClientById(5);
		Guild g = this.guildService.findGuildById(1);
		try {
		this.guildService.leaveGuild(c,g);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		assertTrue(c.getGuild() == null);
	}
	
	@Test 
	void shouldNotLeaveGuild() {
		
		Guild guild = this.guildService.findGuildById(1);
		Client c = this.clientService.findClientById(6);
		this.guildService.leaveGuild(c, guild);
		
		assertTrue(c.getGuild() != null);
	}
	
	//METHOD TO CREATE A GUILD
	private Guild createGuildTesting() {
		
		Guild guild = new Guild();
		
		guild.setCreator("CarlosD");
		guild.setDescription("Best Programming Guild");
		guild.setName("Programming 4ever");
		guild.setLogo("https://i.blogs.es/fd396a/hook/450_1000.jpg");
		
		return guild;
	}
	
	//METHOD TO CREATE A CLIENT
	private Client createClientTesting(String clientUsername) {
		
		Client client = new Client();
		
		User user = new User();
		user.setUsername(clientUsername);
		user.setEnabled(true);
		client.setUser(user);
		
		return client;
	}
	
	
}
