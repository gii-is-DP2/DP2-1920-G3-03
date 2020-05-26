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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String initCreateMessage(ModelMap model)
	{	
		Message message = new Message();
		model.addAttribute("message", message);
		return "client/forums/messagesCreateForm";
	}

	@GetMapping("/client/forums/messagesAnswerForm")
	public String initCreateAnswer(ModelMap model)
	{	
		Message answer = new Message();
		model.addAttribute("answer", answer);
		return "client/forums/messagesAnswerForm";
	}
	
	@GetMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}")
	public String GuildForum(@PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, Model model) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		Guild guild = this.guildService.findGuildById(guildId);
		Forum forum = this.forumService.findForumByGuildId(guildId);
		
		if(!isLoggedUser(clientUsername)||client.getGuild().getId()!=guildId||!forum.getId().equals(forumId)) {
			return "exception";
		}
		
		Collection<Message> messages = this.messageService.findAllForumParentMessages(forumId);
		
		model.addAttribute("guild", guild);
		model.addAttribute("messages", messages);
		
		return "client/forums/messagesList";
	}
	
	@PostMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/create")
	public String processCreateMessage(@Valid Message message, BindingResult result, @PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, ModelMap model, RedirectAttributes redirectAttrs) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		Forum forum = this.forumService.findForumByGuildId(guildId);
		
		if(!isLoggedUser(clientUsername)||client.getGuild().getId()!=guildId||!forum.getId().equals(forumId)) {
			return "exception";
		}
		
		if(result.hasErrors())
		{
			redirectAttrs.addFlashAttribute("wrongMessage", "The message wasn't valid");
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		else
		{
			message.setAnswers(new ArrayList<>());
			message.setCreatedAt(Calendar.getInstance().getTime());
			message.setEdited(false);
			message.setIsParent(true);
			message.setUser(client.getUser());
			
			forum.getMessages().add(message);
					
			this.forumService.saveForum(forum);
			
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		
	}
	
	@PostMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}/messages/{messageId}")
	public String processCreateAnswer(@Valid Message answer, BindingResult result, @PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, 
		@PathVariable("messageId") String msgId, ModelMap model, RedirectAttributes redirectAttrs) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		Forum forum = this.forumService.findForumByGuildId(guildId);
		Message message = this.messageService.findMessageFromId(Integer.valueOf(msgId));
		
		if(!isLoggedUser(clientUsername)||client.getGuild().getId()!=guildId||!forum.getId().equals(forumId)||!forum.getMessages().contains(message)||!message.getIsParent()) {
			return "exception";
		}
		
		if(result.hasErrors())
		{
			redirectAttrs.addFlashAttribute("wrongMessage", "The message wasn't valid");
			return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
		}
		else
		{
			answer.setAnswers(new ArrayList<>());
			answer.setCreatedAt(Calendar.getInstance().getTime());
			answer.setEdited(false);
			answer.setIsParent(false);
			answer.setUser(client.getUser());
			
			message.getAnswers().add(answer);
			
			forum.getMessages().add(answer);
			
			try
			{
				this.messageService.saveMessage(message);				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				redirectAttrs.addFlashAttribute("wrongMessage", "The message wasn't valid");
				return "redirect:/client/"+clientUsername + "/guilds/" + guildId + "/forums/" + forumId;
			}
			
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
