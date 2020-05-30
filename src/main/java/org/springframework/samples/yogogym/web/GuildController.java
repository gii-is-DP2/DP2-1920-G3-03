package org.springframework.samples.yogogym.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ForumService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.exceptions.GuildLogoException;
import org.springframework.samples.yogogym.service.exceptions.GuildNotCreatorException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameCreatorException;
import org.springframework.samples.yogogym.service.exceptions.GuildSameNameException;
import org.springframework.samples.yogogym.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GuildController {

	// GENERAL
	private static final String EXCEPTION = "exception";
	private static final String GUILD_ATTR = "guild";
	private static final String CLIENT = "client";

	// CLIENT VIEWS
	private static final String CLIENT_CREATE_OR_UPDATE = "client/guilds/guildsCreateOrUpdate";
	private static final String CLIENT_REDIRECT = "redirect:/client/{clientUsername}/guilds";

	private final ClientService clientService;
	private final GuildService guildService;
	private final ForumService forumService;

	@Autowired
	public GuildController(final ClientService clientService, final GuildService guildService,
			final ForumService forumService) {
		this.clientService = clientService;
		this.guildService = guildService;
		this.forumService = forumService;
	}

	@GetMapping("/client/{clientUsername}/guilds")
	public String clientGuildList(@PathVariable("clientUsername") String clientUsername, Model model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Client client = this.clientService.findClientByUsername(clientUsername);
		Collection<Guild> allGuilds = this.guildService.findAllGuild();

		model.addAttribute(CLIENT, client);
		model.addAttribute("allGuilds", allGuilds);
		return "client/guilds/guildsList";
	}

	@GetMapping("/client/{clientUsername}/guilds/{guildId}")
	public String clientGuildDetails(@PathVariable("clientUsername") String clientUsername,
			@PathVariable("guildId") int guildId, Model model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Guild guild = this.guildService.findGuildById(guildId);
		Collection<Client> clients = this.guildService.findAllClientesByGuild(guild);
		Client client = this.clientService.findClientByUsername(clientUsername);
		Integer points = 0;
		for (Client c : clients) {
			Collection<Inscription> ins = c.getInscriptions();
			for (Inscription i : ins) {
				if (i.getStatus().equals(Status.SUBMITTED)) {
					points += i.getChallenge().getPoints();
				}
			}
		}
		int forumId = this.forumService.findForumIdByGuildId(guildId);

		model.addAttribute(CLIENT, client);
		model.addAttribute("clients", clients.size());
		model.addAttribute("points", points);
		model.addAttribute(GUILD_ATTR, guild);
		model.addAttribute("forumId", forumId);

		return "client/guilds/guildsDetails";
	}

	@GetMapping("client/{clientUsername}/guilds/create")
	public String initGuildCreateForm(@PathVariable("clientUsername") String clientUsername, final ModelMap model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Guild guild = new Guild();
		Client client = this.clientService.findClientByUsername(clientUsername);
		guild.setCreator(clientUsername);
		model.addAttribute(GUILD_ATTR, guild);
		model.addAttribute(CLIENT, client);

		return CLIENT_CREATE_OR_UPDATE;

	}

	@PostMapping("client/{clientUsername}/guilds/create")
	public String processGuildCreationForm(@Valid Guild g, BindingResult result,
			@PathVariable("clientUsername") String clientUsername, final ModelMap model)
			throws GuildSameNameException, GuildSameCreatorException, GuildLogoException {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Client client = this.clientService.findClientByUsername(clientUsername);

		if (result.hasErrors()) {

			model.addAttribute(CLIENT, client);
			model.addAttribute(GUILD_ATTR, g);

			return CLIENT_CREATE_OR_UPDATE;
		} else {

			try {
				client.setGuild(g);
				this.guildService.saveGuild(g, client);
			} catch (GuildNotCreatorException ex) {
				result.rejectValue("creator", "required: ", "The creator has to be the same as the user logged");
				return CLIENT_CREATE_OR_UPDATE;
			} catch (GuildSameCreatorException ex) {
				result.rejectValue("creator", "required: ", "There is already a guild created by this creator");
				return CLIENT_CREATE_OR_UPDATE;
			} catch (GuildLogoException ex) {
				result.rejectValue("logo", "required: ", "The link must start with https://");
				return CLIENT_CREATE_OR_UPDATE;
			} catch (GuildSameNameException ex) {
				result.rejectValue("name", "required: ", "There is already a guild with that name");
				return CLIENT_CREATE_OR_UPDATE;
			}
			return "redirect:/client/" + clientUsername + "/guilds";
		}
	}

	@GetMapping("client/{clientUsername}/guilds/{guildId}/edit")
	public String initGuildEditForm(@PathVariable("clientUsername") String clientUsername,
			@PathVariable("guildId") int guildId, final ModelMap model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Guild guild = this.guildService.findGuildById(guildId);
		if (!guild.getCreator().equals(clientUsername))
			return EXCEPTION;
		Client client = this.clientService.findClientByUsername(clientUsername);
		guild.setCreator(clientUsername);
		model.addAttribute(GUILD_ATTR, guild);
		model.addAttribute(CLIENT, client);

		return CLIENT_CREATE_OR_UPDATE;

	}

	@PostMapping("client/{clientUsername}/guilds/{guildId}/edit")
	public String processGuildEditForm(@PathVariable("clientUsername") String clientUsername,
			@PathVariable("guildId") int guildId, @Valid Guild guildAtr, BindingResult result, ModelMap model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Client client = this.clientService.findClientByUsername(clientUsername);
		if (result.hasErrors()) {
			model.put(GUILD_ATTR, guildAtr);

			model.addAttribute(CLIENT, client);

			return CLIENT_CREATE_OR_UPDATE;
		} else {
			try {
				guildAtr.setId(guildId);
				this.guildService.saveGuild(guildAtr, client);
			} catch (GuildNotCreatorException ex) {
				result.rejectValue("creator", "required: ", "The creator has to be the same as the user logged");
				return CLIENT_CREATE_OR_UPDATE;
			} catch (GuildSameCreatorException ex) {
				result.rejectValue("creator", "required: ", "There is already a guild created by this creator");
				return CLIENT_CREATE_OR_UPDATE;
			} catch (GuildLogoException ex) {
				result.rejectValue("logo", "required: ", "The link must start with https://");
				return CLIENT_CREATE_OR_UPDATE;
			} catch (GuildSameNameException ex) {
				result.rejectValue("name", "required: ", "There is already a guild with that name");
				return CLIENT_CREATE_OR_UPDATE;
			}
			return "redirect:/client/" + clientUsername + "/guilds/" + guildId;
		}
	}

	@GetMapping("client/{clientUsername}/guilds/{guildId}/delete")
	public String deleteGuild(@PathVariable("clientUsername") String clientUsername,
			@PathVariable("guildId") int guildId, RedirectAttributes redirectAttrs, Model model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Guild guild = this.guildService.findGuildById(guildId);
		redirectAttrs.addFlashAttribute("deleteMessage", "The Guild was deleted successfully");
		if (!guild.getCreator().equals(clientUsername))
			return EXCEPTION;

		this.guildService.deleteGuild(guild, clientUsername);
		return CLIENT_REDIRECT;
	}

	@GetMapping("client/{clientUsername}/guilds/{guildId}/join")
	public String joinGuild(@PathVariable("clientUsername") String clientUsername, @PathVariable("guildId") int guildId,
			Model model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Guild guild = this.guildService.findGuildById(guildId);

		this.guildService.joinGuild(clientUsername, guild);

		return CLIENT_REDIRECT;
	}

	@GetMapping("client/{clientUsername}/guilds/{guildId}/leave")
	public String leaveGuild(@PathVariable("clientUsername") String clientUsername,
			@PathVariable("guildId") int guildId, Model model) {

		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Client client = this.clientService.findClientByUsername(clientUsername);
		Guild guild = this.guildService.findGuildById(guildId);
		if (!client.getGuild().equals(guild))
			return EXCEPTION;

		this.guildService.leaveGuild(client, guild);
		return CLIENT_REDIRECT;
	}

//	Boolean checkGuildNotSameName(final Guild guild, Collection<Guild> guilds) {
//		Boolean res = true;
//
//		// Para Sprint 2 cambiar a un método de service y realziar respectivas pruebas
//		Error: for (Guild g : guilds) {
//			if (g.getId() != guild.getId()) {
//				if (g.getName().trim().toLowerCase().equals(guild.getName().trim().toLowerCase())) {
//					res = false;
//					break Error;
//				}
//			}
//		}
//
//		return res;
//	}
}
