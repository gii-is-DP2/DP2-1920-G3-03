package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.ChallengeWithInscriptionsException;
import org.springframework.samples.yogogym.service.exceptions.GuildLogoException;
import org.springframework.samples.yogogym.service.exceptions.GuildNotUserException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameCreatorException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GuildController {
	

	private final ClientService clientService;
	private final GuildService guildService;
	
	@Autowired
	public GuildController(final ClientService clientService, final GuildService guildService) {
		this.clientService = clientService;
		this.guildService = guildService;
	}
	
	
	@GetMapping("/client/{clientUsername}/guilds")
	public String ClienGuildList(@PathVariable("clientUsername") String clientUsername, Model model) {
		Client client = this.clientService.findClientByUsername(clientUsername);
		Collection<Guild> allGuilds = this.guildService.findAllGuild();
		
		model.addAttribute("client", client);
		model.addAttribute("allGuilds",allGuilds);
		return "client/guilds/guildsList";
	}
	
	@GetMapping("/client/{clientUsername}/guilds/{guildId}")
	public String ClientGuildDetails(@PathVariable("clientUsername") String clientUsername,
			 @PathVariable("guildId") int guildId, Model model) {
		Guild guild = this.guildService.findGuildById(guildId);
		Collection<Client> clients = this.guildService.findAllClientesByGuild(guild);
		Client client = this.clientService.findClientByUsername(clientUsername);
		Integer points = 0;
		for(Client c : clients) {
			Collection<Inscription> ins = c.getInscriptions();
			for(Inscription i : ins) {
				if(i.getStatus().equals(Status.SUBMITTED)) {
					points+=i.getChallenge().getPoints();
				}
			}
		}
		
		model.addAttribute("client",client);
		model.addAttribute("clients",clients.size());
		model.addAttribute("points",points);
		model.addAttribute("guild", guild);

		return "client/guilds/guildsDetails";
	}
	
	@GetMapping("client/{clientUsername}/guilds/create")
	public String initGuildCreateForm(@PathVariable("clientUsername") String clientUsername,
			final ModelMap model) {
		
		Guild guild = new Guild();
		Client client = this.clientService.findClientByUsername(clientUsername);
		guild.setCreator(clientUsername);
		model.addAttribute("guild", guild);
		model.addAttribute("client", client);
			
		return "client/guilds/guildsCreateOrUpdate";
		
	}
	
	@PostMapping("client/{clientUsername}/guilds/create")
	public String processGuildCreationForm(@Valid Guild guild,BindingResult result,
			@PathVariable("clientUsername") String clientUsername, final ModelMap model) {

		Client client = this.clientService.findClientByUsername(clientUsername);

		Collection<Guild> guilds = this.guildService.findAllGuild();
		
		if(guild.getId() != null) {
			guilds = guilds.stream().filter(c -> c.getId() != guild.getId()).collect(Collectors.toList());
		}
		
		
		
		if (result.hasErrors()) {
			
			model.addAttribute("client", client);
			model.addAttribute("guild", guild);
			return "client/guilds/guildsCreateOrUpdate";
			
		} else {
		
			model.addAttribute("guild", guild);
			
			if(guilds.stream().anyMatch(c->c.getName().equals(guild.getName()))) {
					result.rejectValue("name", "required: ", "There is already a guild with that name");
					return "client/guilds/guildsCreateOrUpdate";
			}else if (guilds.stream().anyMatch(c->c.getCreator().equals(guild.getCreator()))) {
					result.rejectValue("creator", "required: ", "There is already a guild created by this creator");
					return "client/guilds/guildsCreateOrUpdate";
			}else if(!guild.getLogo().startsWith("https://")) {
					result.rejectValue("logo", "required: ", "The link must start with https://");
					return "client/guilds/guildsCreateOrUpdate";
			}else {
				client.setGuild(guild);
				this.guildService.saveGuild(guild);
				return "redirect:/client/" + clientUsername + "/guilds";
			
			}
				
			
		}
	}
	
	
	@GetMapping("client/{clientUsername}/guilds/{guildId}/edit")
	public String initGuildEditForm(@PathVariable("clientUsername") String clientUsername,@PathVariable("guildId") int guildId,
			final ModelMap model) {
	
		Guild guild = this.guildService.findGuildById(guildId);
		if(!guild.getCreator().equals(clientUsername)) 
			return "exception";
		Client client = this.clientService.findClientByUsername(clientUsername);
		guild.setCreator(clientUsername);
		model.addAttribute("guild", guild);
		model.addAttribute("client", client);
			
		return "client/guilds/guildsCreateOrUpdate";
		
	}
	
	@PostMapping("client/{clientUsername}/guilds/{guildId}/edit")
	public String processGuildEditForm(@PathVariable("clientUsername") String clientUsername,
			@PathVariable("guildId") int guildId, @Valid Guild guild,  BindingResult result,ModelMap model) {
			
		Collection<Guild> guilds = this.guildService.findAllGuild();
			
		if(guild.getId() != null) 
			guilds = guilds.stream().filter(c -> c.getId() != guild.getId()).collect(Collectors.toList());
		
		if(result.hasErrors()) {
				
				model.put("guild", guild);
				Client client = this.clientService.findClientByUsername(clientUsername);
				model.addAttribute("client", client);
				
				return "client/guilds/guildsCreateOrUpdate";
				
			}else {
				model.addAttribute("guild", guild);
				
				}if(guilds.stream().anyMatch(c->c.getName().equals(guild.getName()))) {
						result.rejectValue("name", "required: ", "There is already a guild with that name");
						return "client/guilds/guildsCreateOrUpdate";
				}else if(!guild.getLogo().startsWith("https://")) {
						result.rejectValue("logo", "required: ", "The link must start with https://");
						return "client/guilds/guildsCreateOrUpdate";
				}else {
					guild.setId(guildId);
					this.guildService.saveGuild(guild);
					return "redirect:/client/" + clientUsername + "/guilds/"+guildId;
				}
				
			}
	
	
	@GetMapping("client/{clientUsername}/guilds/{guildId}/delete")
	public String deleteGuild(@PathVariable("clientUsername") String clientUsername,@PathVariable("guildId") int guildId, RedirectAttributes redirectAttrs, Model model) {
		
		Guild guild = this.guildService.findGuildById(guildId);
		redirectAttrs.addFlashAttribute("deleteMessage", "The Guild was deleted successfully");
		if(!guild.getCreator().equals(clientUsername))
			return "exception";
			
		this.guildService.deleteGuild(guild,clientUsername);
		return "redirect:/client/{clientUsername}/guilds";
	}
	
	@GetMapping("client/{clientUsername}/guilds/{guildId}/join")
	public String joinGuild(@PathVariable("clientUsername") String clientUsername, @PathVariable("guildId")int guildId, Model model){
		
		Guild guild = this.guildService.findGuildById(guildId);
		
		this.guildService.joinGuild(clientUsername, guild);
		
		return "redirect:/client/{clientUsername}/guilds";
	}
	
	@GetMapping("client/{clientUsername}/guilds/{guildId}/leave")
	public String leaveGuild(@PathVariable("clientUsername") String clientUsername, @PathVariable("guildId")int guildId, Model model){
	
		Client client = this.clientService.findClientByUsername(clientUsername);
		Guild guild = this.guildService.findGuildById(guildId);
		if(!client.getGuild().equals(guild))
			return "exception";
		
		this.guildService.leaveGuild(client,guild);
		return "redirect:/client/{clientUsername}/guilds";
	}
}
