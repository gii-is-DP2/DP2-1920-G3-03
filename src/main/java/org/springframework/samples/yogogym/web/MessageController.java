package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Forum;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ForumService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.MessageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {
	
	private final MessageService messageService;
	private final ClientService clientService;
	private final GuildService guildService;
	private final ForumService forumService;
	
	@Autowired
	public MessageController(final MessageService messageService,final ClientService clientService,final GuildService guildService,final ForumService forumService) {
		this.messageService = messageService;
		this.clientService = clientService;
		this.guildService = guildService;
		this.forumService = forumService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/client/forums/messagesCreateForm")
	public String getMessageForm()
	{		
		return "client/forums/messagesCreateForm";
	}

	@GetMapping("/client/forums/messagesAnswerForm")
	public String getAnswerForm()
	{		
		return "client/forums/messagesAnswerForm";
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
	
	@PostMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create")
	public String processCreateMessage(@PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, 
		@Valid Message message, Model model, BindingResult result) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		if(!isLoggedUser(clientUsername)||client.getGuild().getId()!=guildId) {
			return "exception";
		}
		
		if(result.hasErrors())
		{
			model.addAttribute("Error");
			
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		else
		{
			
			Forum f = this.forumService.findForumByGuildId(guildId);
			
			message.setAnswers(new ArrayList<>());
			message.setCreatedAt(Calendar.getInstance().getTime());
			message.setEdited(false);
			message.setIsParent(true);
			message.setUser(client.getUser());
			
			f.getMessages().add(message);
					
			this.forumService.saveForum(f);
			
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		
	}
	
	@PostMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}")
	public String processCreateAnswer(@PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, 
		@PathVariable("messageId") String msgId, @Valid Message message, Model model, BindingResult result) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		if(!isLoggedUser(clientUsername)||client.getGuild().getId()!=guildId) {
			return "exception";
		}
		
		if(result.hasErrors())
		{
			model.addAttribute("Error");
			
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		else
		{
			Message m = this.messageService.findMessageFromId(Integer.valueOf(msgId));
						
			message.setAnswers(new ArrayList<>());
			message.setCreatedAt(Calendar.getInstance().getTime());
			message.setEdited(false);
			message.setIsParent(false);
			message.setUser(client.getUser());
			
			m.getAnswers().add(message);
					
			this.messageService.saveMessage(m);
			
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		
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
