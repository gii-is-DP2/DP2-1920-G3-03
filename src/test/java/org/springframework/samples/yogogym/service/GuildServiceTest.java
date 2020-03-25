package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.service.exceptions.GuildLogoException;
import org.springframework.samples.yogogym.service.exceptions.GuildNotUserException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameCreatorException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameNameException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GuildServiceTest {

	@Autowired
	protected GuildService guildService;
	@Autowired
	protected ClientService clientService;
	
	
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
		assertThat(clients.size()).isEqualTo(3);
	}
	
	@Test
	void shouldSaveGuild() {
		
		Guild guild = createGuildTesting();
		
		try {
			this.guildService.saveGuild(guild);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		guild = this.guildService.findGuildById(guild.getId());
		assertThat(guild.getCreator().equals("CarlosD"));
		assertThat(guild.getName().equals("Programming 4ever"));
		assertThat(guild.getDescription().equals("Best Programming Guild"));
		assertThat(guild.getLogo().equals("https://i.blogs.es/fd396a/hook/450_1000.jpg"));
	}
	
	@Test
	void shouldNotSaveGuildWithSameName() {
		
		Guild g1 = createGuildTesting();
		Guild g2 = createGuildTesting();
		
		g1.setName("Name");
		g2.setName("Name");
		
		
		this.guildService.saveGuild(g1);
		Collection<Guild> guilds = this.guildService.findAllGuild();
		int foundBefore = guilds.size();
		
		this.guildService.saveGuild(g2);
		guilds = this.guildService.findAllGuild();
		int foundAfter = guilds.size();
		
		assertThat(foundBefore).isEqualTo(foundAfter);		

	}
	
	@Test
	void shouldNotSaveGuildWithSameCreator() {
		
		Guild g1 = createGuildTesting();
		Guild g2 = createGuildTesting();
		
		g1.setCreator("CarlosD");
		g2.setCreator("CarlosD");
		g2.setName("GymPrueba");
		
		this.guildService.saveGuild(g1);
		Collection<Guild> guilds = this.guildService.findAllGuild();
		int foundBefore = guilds.size();
		
		this.guildService.saveGuild(g2);
		guilds = this.guildService.findAllGuild();
		int foundAfter = guilds.size();
		
		assertThat(foundBefore).isEqualTo(foundAfter);	
	}
	
	@Test
	void shouldNotSaveGuildWithBadURL() {
		
		Collection<Guild> guilds = this.guildService.findAllGuild();
		int foundBefore = guilds.size();
		
		Guild g1 = createGuildTesting();
		g1.setLogo("EstaUrlEstaMal.com");
				
		this.guildService.saveGuild(g1);
		
		guilds = this.guildService.findAllGuild();
		int foundAfter = guilds.size();
		
		assertThat(foundBefore).isEqualTo(foundAfter);
			

	}
	
	@Test
	void shouldDeleteGuild() {
		
		
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
	
	@Test
	void shouldJoinGuild() {
		
		Client c = this.clientService.findClientById(6);
		Guild g = this.guildService.findGuildById(1);
		this.guildService.joinGuild(c.getUser().getUsername(), g);
		assertTrue(c.getGuild().equals(g));
	}
	
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
	
	private Guild createGuildTesting() {
		
		Guild guild = new Guild();
		
		guild.setCreator("CarlosD");
		guild.setDescription("Best Programming Guild");
		guild.setName("Programming 4ever");
		guild.setLogo("https://i.blogs.es/fd396a/hook/450_1000.jpg");
		
		return guild;
	}
	
	
}
