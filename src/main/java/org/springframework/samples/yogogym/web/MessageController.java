package org.springframework.samples.yogogym.web;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.MessageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {
	
	private final MessageService messageService;
	private final ClientService clientService;
	private final GuildService guildService;
	
	@Autowired
	public MessageController(final MessageService messageService,final ClientService clientService,final GuildService guildService) {
		this.messageService = messageService;
		this.clientService = clientService;
		this.guildService = guildService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}")
	public String GuildForum(@PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, Model model) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		Guild guild = this.guildService.findGuildById(guildId);
		
		if(!isLoggedUser(clientUsername)||client.getGuild().getId()!=guildId) {
			return "exception";
		}
		
		Collection<Message> messages = this.messageService.findAllForumParentMessages(forumId);
		
		model.addAttribute("guild", guild);
		model.addAttribute("messages", messages);
		
		return "client/forums/messagesList";
	}
	
	private Boolean isLoggedUser(final String usernameURL) {
			
		User user;
		
		Client client = this.clientService.findClientByUsername(usernameURL);
		user = client.getUser();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		else {
			username = principal.toString();
		}
		
		return user.getUsername().equals(username);
	}
}
