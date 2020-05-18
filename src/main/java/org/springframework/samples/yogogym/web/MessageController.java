package org.springframework.samples.yogogym.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {
	
	private final MessageService messageService;
	
	@Autowired
	public MessageController(final MessageService messageService) {
		this.messageService = messageService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	// TRAINER

	@GetMapping("/client/{clientUsername}/guilds/{guildId}/forums/{forumId}")
	public String GuildForum(@PathVariable("clientUsername") String clientUsername,
		@PathVariable("guildId") int guildId, @PathVariable("forumId") int forumId, Model model) {
		
		Collection<Message> messages = this.messageService.findAllForumParentMessages(forumId);
		
		model.addAttribute("messages", messages);
		
		return "client/forums/messagesList";
	}
	
}
