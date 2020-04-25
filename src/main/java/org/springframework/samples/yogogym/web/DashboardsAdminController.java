package org.springframework.samples.yogogym.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DashboardsAdminService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardsAdminController {

	private final DashboardsAdminService dashboardsAdminService;
	private final ClientService clientService;
	private final InscriptionService inscriptionService;
	private final GuildService guildService;

	@Autowired
	public DashboardsAdminController(final DashboardsAdminService dashboardsAdminService, ClientService clientService,
			InscriptionService inscriptionService, GuildService guildService) {
		this.dashboardsAdminService = dashboardsAdminService;
		this.clientService = clientService;
		this.inscriptionService = inscriptionService;
		this.guildService = guildService;
	}

	@GetMapping("/admin/dashboardEquipment")
	public String getDashboardEquipment(ModelMap model) {
		dashboard(28, "Month", model);
		dashboard(7, "Week", model);
		return "admin/dashboards/dashboardEquipment";
	}
	
	private void dashboard(Integer days, String string, ModelMap model) {
		List<Integer> countEquipment = this.dashboardsAdminService.countEquipment(days);
		List<String> nameEquipment = this.dashboardsAdminService.nameEquipment(days);
		if (!countEquipment.isEmpty() || !nameEquipment.isEmpty()) {
			String[] s = new String[nameEquipment.size()];
			for (int i = 0; i < nameEquipment.size(); i++) {
				s[i] = nameEquipment.get(i);
			}
			Integer[] c = new Integer[countEquipment.size()];
			for (int i = 0; i < countEquipment.size(); i++) {
				c[i] = countEquipment.get(i);
			}
			model.addAttribute("orderName" + string, s);
			model.addAttribute("count" + string, c);
			model.addAttribute("hasEquipment" + string, true);
		} else {
			model.addAttribute("hasEquipment" + string, false);
		}

	}

	@GetMapping("/admin/dashboardChallenges/{month}")
	public String getDashboardChallenges(@PathVariable("month") int month, ModelMap model) {

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		if (month == 0)
			month = cal.get(Calendar.MONTH) + 1;
		List<Challenge> challenges = (List<Challenge>) this.dashboardsAdminService.getChallengesOfMonth(month);

		if (!challenges.isEmpty()) {
			model.addAttribute("ChallengesExists", true);
			dashboardChallenges(challenges, month, model);
		} else {
			model.addAttribute("ChallengesExists", false);
		}

		return "admin/dashboards/dashboardChallenges";
	}

	private void dashboardChallenges(List<Challenge> challenges, int month, ModelMap model) {

		// client with more points
		Client client = null;
		Integer cPoints = null;

		List<Inscription> completedInscriptionsThisMonth = this.dashboardsAdminService.findCompletedInscriptionsThisMonth(month);
		if (completedInscriptionsThisMonth.isEmpty()) {
			model.addAttribute("NoCompletedChallenges", true);
			return;
		}

		List<Client> clients = (List<Client>) this.clientService.findAllClient();
		for (Client c : clients) {
			Integer auxPoints = 0;
			for (Inscription i : c.getInscriptions()) {
				if (completedInscriptionsThisMonth.contains(i)) {
					auxPoints += i.getChallenge().getPoints();
				}
			}
			if (cPoints == null || auxPoints > cPoints) {
				client = c;
				cPoints = auxPoints;
			}
		}

		model.addAttribute("client", client);
		model.addAttribute("cPoints", cPoints);

		// Guild with more points
		Guild guild = null;
		Integer gPoints = null;

		List<Guild> Allguilds = (List<Guild>) this.guildService.findAllGuild();
		for (Guild g : Allguilds) {

			clients = (List<Client>) this.guildService.findAllClientesByGuild(g);
			Integer auxcPoints = 0;
			for (Client c : clients) {
				for (Inscription i : c.getInscriptions()) {
					if (completedInscriptionsThisMonth.contains(i)) {
						auxcPoints += i.getChallenge().getPoints();
					}
				}
			}

			if (gPoints == null || auxcPoints > gPoints) {
				guild = g;
				gPoints = auxcPoints;
			}
		}

		model.addAttribute("guild", guild);
		model.addAttribute("gPoints", gPoints);

		// Graphs
		String[] challengesNames = new String[challenges.size()];
		for (int i = 0; i < challenges.size(); i++) {
			challengesNames[i] = challenges.get(i).getName();
		}
		model.addAttribute("challengesNames", challengesNames);

		// Percentage of clients who completed each challenge
		Integer numberOfClients = this.clientService.findAllClient().size();
		Double[] percentageClients = new Double[challenges.size()];

		for (int i = 0; i < challenges.size(); i++) {
			Double clientsWhoCompleted = (double) this.inscriptionService
					.findInscriptionsByChallengeId(challenges.get(i).getId()).stream()
					.filter(ins -> ins.getStatus().equals(Status.COMPLETED)).count();
			percentageClients[i] = (clientsWhoCompleted / numberOfClients) * 100;
		}

		model.addAttribute("percentageClients", percentageClients);

		// Percentage of Guilds who completed each challenge
		List<Guild> guilds = (List<Guild>) this.guildService.findAllGuild();
		Integer numberOfGuilds = guilds.size();
		Double[] percentageGuilds = new Double[challenges.size()];

		for (int i = 0; i < challenges.size(); i++) {
			Double guildsWhoCompleted = 0.;
			Challenge cll = challenges.get(i);
			for (Guild g : guilds) {
				if (this.guildService.findAllClientesByGuild(g).stream().anyMatch(c -> c.getInscriptions().stream()
						.anyMatch(ins -> ins.getChallenge().equals(cll) && ins.getStatus().equals(Status.COMPLETED))))
					guildsWhoCompleted += 1.;
			}
			percentageGuilds[i] = (guildsWhoCompleted / numberOfGuilds) * 100;
		}

		model.addAttribute("percentageGuilds", percentageGuilds);

	}

	@GetMapping("/admin/dashboardGeneral")
	public String getDashboardGeneral( ModelMap model) {

		Integer clients = this.dashboardsAdminService.countClients();
		Integer trainers = this.dashboardsAdminService.countTrainers();
		List<Integer> clientsPerGuild = this.dashboardsAdminService.countClientsPerGuild();

		// Graphs
		// Clients per Guilds
		List<String> guildNames = (List<String>) this.guildService.findAllGuildNames();
		guildNames.add(0, "Without guild");
		model.addAttribute("guildNames", guildNames);
		model.addAttribute("clientsPerGuild", clientsPerGuild);


		boolean dataExists = clients > 0  || trainers >0;
		if (dataExists) {
			model.addAttribute("dataExists", true);
			model.addAttribute("clients", clients);
			model.addAttribute("trainers", trainers);
		} else {
			model.addAttribute("dataExists", false);
		}

		return "admin/dashboards/dashboardGeneral";
	}
}
