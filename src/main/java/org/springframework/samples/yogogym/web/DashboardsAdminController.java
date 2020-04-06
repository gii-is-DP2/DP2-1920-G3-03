package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DashboardsAdminService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardsAdminController {

	private final DashboardsAdminService dashboardsAdminService;
	private final ClientService clientService;
	private final InscriptionService inscriptionService;
	private final GuildService guildService;

	@Autowired
	public DashboardsAdminController(final DashboardsAdminService dashboardsAdminService, ClientService clientService, InscriptionService inscriptionService, GuildService guildService) {
		this.dashboardsAdminService = dashboardsAdminService;
		this.clientService = clientService;
		this.inscriptionService = inscriptionService;
		this.guildService = guildService;
	}

	@GetMapping("/admin/dashboardEquipment")
	public String getDashboardEquipment(Model model) {
		Collection<Training> listTraining = this.dashboardsAdminService.equipmentControl();
		if (!listTraining.isEmpty()) {
			dashboardEquipment(listTraining, 28, "Month", model);
			dashboardEquipment(listTraining, 7, "Week", model);
		} else {
			model.addAttribute("hasEquipmentMonth", false);
			model.addAttribute("hasEquipmentWeek", false);
		}
		return "admin/dashboards/dashboardEquipment";
	}
	
	@GetMapping("/admin/dashboardChallenges/{month}")
	public String getDashboardChallenges(@PathVariable("month") int month, Model model) {
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		if(month == 0)
			month = cal.get(Calendar.MONTH) + 1;
		List<Challenge> challenges = (List<Challenge>) this.dashboardsAdminService.getChallengesOfMonth(month);
		
		if(!challenges.isEmpty()) {
			model.addAttribute("ChallengesExists",true);
			dashboardChallenges(challenges,month, model);
		}
		else {
			model.addAttribute("ChallengesExists",false);
		}
		
		return "admin/dashboards/dashboardChallenges";
	}

	private void dashboardChallenges(List<Challenge> challenges,int month, Model model) {
		
		// client with more points
		Client client = null;
		Integer cPoints = null;
		
		List<Inscription> completedInscriptionsThisMonth = this.inscriptionService.findAll().stream().filter(i -> challenges.contains(i.getChallenge()) && i.getStatus().equals(Status.COMPLETED)).collect(Collectors.toList());
		if(completedInscriptionsThisMonth.isEmpty()) {
			model.addAttribute("NoCompletedChallenges", true);
			return;
		}
		//model.addAttribute("NoCompletedChallenges", false);
		
		List<Client> clients = (List<Client>) this.clientService.findAllClient();
		for(Client c : clients) {
			Integer auxPoints = 0;
			for(Inscription i : c.getInscriptions()) {
				if(completedInscriptionsThisMonth.contains(i)) {
					auxPoints += i.getChallenge().getPoints();
				}
			}
			if(cPoints == null || auxPoints > cPoints) {
				client = c;
				cPoints = auxPoints;
			}
		}
		
		model.addAttribute("client",client);
		model.addAttribute("cPoints",cPoints);
		
		// Guild with more points
		Guild guild = null;
		Integer gPoints = null;
		
		List<Guild> Allguilds = (List<Guild>) this.guildService.findAllGuild();
		for(Guild g : Allguilds) {
			
			clients = (List<Client>) this.guildService.findAllClientesByGuild(g);
			Integer auxcPoints = 0;
			for(Client c : clients) {
				for(Inscription i : c.getInscriptions()) {
					if(completedInscriptionsThisMonth.contains(i)) {
						auxcPoints += i.getChallenge().getPoints();
					}
				}
			}
			
			if(gPoints == null || auxcPoints > gPoints) {
				guild = g;
				gPoints = auxcPoints;
			}
		}
		
		model.addAttribute("guild",guild);
		model.addAttribute("gPoints",gPoints);
		
		
		//Graphs
		String[] challengesNames = new String[challenges.size()];
		for(int i=0; i<challenges.size(); i++) {
			challengesNames[i] = challenges.get(i).getName();
		}
		model.addAttribute("challengesNames",challengesNames);
		
		// Percentage of clients who completed each challenge
		Integer numberOfClients = this.clientService.findAllClient().size();
		Double[] percentageClients = new Double[challenges.size()];
		
		for(int i=0; i<challenges.size(); i++) {
			Double clientsWhoCompleted = (double) this.inscriptionService.findInscriptionsByChallengeId(challenges.get(i).getId()).stream().filter(ins -> ins.getStatus().equals(Status.COMPLETED)).count();
			percentageClients[i] = (clientsWhoCompleted/numberOfClients) *100;
		}
		
		model.addAttribute("percentageClients",percentageClients);
		
		// Percentage of Guilds who completed each challenge
		List<Guild> guilds = (List<Guild>) this.guildService.findAllGuild();
		Integer numberOfGuilds = guilds.size();
		Double[] percentageGuilds = new Double[challenges.size()];
		
		for(int i=0; i<challenges.size(); i++) {
			Double guildsWhoCompleted = 0.;
			Challenge cll = challenges.get(i);
			for(Guild g : guilds) {
				if(this.guildService.findAllClientesByGuild(g).stream().anyMatch(c -> c.getInscriptions().stream().anyMatch(ins -> ins.getChallenge().equals(cll) && ins.getStatus().equals(Status.COMPLETED))))
					guildsWhoCompleted += 1.;
			}
			percentageGuilds[i] = (guildsWhoCompleted/numberOfGuilds) *100;
		}
		
		model.addAttribute("percentageGuilds",percentageGuilds);
		
	}

	private void dashboardEquipment(Collection<Training> listTraining, Integer days, String string, Model model) {
		List<Integer> listTrainingFilter = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -days);
		Date d2 = now.getTime();
		for (Training t : listTraining) {
			if (t.getInitialDate().before(new Date()) && t.getInitialDate().after(d2)) {
				listTrainingFilter.add(t.getId());
			}
		}
		if (!listTrainingFilter.isEmpty()) {
			List<Integer> listRoutine = new ArrayList<>();
			for (Integer x : listTrainingFilter) {
				listRoutine.addAll(this.dashboardsAdminService.listRoutine(x));
			}
			List<Integer> listRepsRoutine = new ArrayList<>();
			for (Integer x : listRoutine) {
				if (x != null) {
					Integer aux = this.dashboardsAdminService.listRepsRoutine(x);
					if (aux != null && aux != 0) {
						for (int i = 0; i < aux; i++) {
							listRepsRoutine.add(x);
						}
					}
				}
			}
			if (!listRepsRoutine.isEmpty()) {
				List<Integer> listExercise = new ArrayList<>();
				for (Integer x : listRepsRoutine) {
					listExercise.addAll(this.dashboardsAdminService.listExercise(x));
				}
				List<Integer> listIdEquipment = new ArrayList<>();
				for (Integer x : listExercise) {
					if (x != null) {
						Integer aux = this.dashboardsAdminService.listIdEquipment(x);
						if (aux != null) {
							listIdEquipment.add(aux);
						}
					}
				}
				if (!listIdEquipment.isEmpty()) {
					List<String> listNameEquipment = new ArrayList<>();
					for (Integer x : listIdEquipment) {
						listNameEquipment.add(this.dashboardsAdminService.listNameEquipment(x));
					}
					Set<String> aux = new HashSet<>(listNameEquipment);
					List<String> orderName = new ArrayList<>();
					orderName.addAll(aux);
					List<Integer> count = new ArrayList<>();
					for (String s : orderName) {
						count.add(Collections.frequency(listNameEquipment, s));
					}
					String[] s = new String[orderName.size()];
					for (int i = 0; i < orderName.size(); i++) {
						s[i] = orderName.get(i);
					}
					Integer[] c = new Integer[count.size()];
					for (int i = 0; i < count.size(); i++) {
						c[i] = count.get(i);
					}
					model.addAttribute("orderName" + string, s);
					model.addAttribute("count" + string, c);
					model.addAttribute("hasEquipment" + string, true);
				} else {
					model.addAttribute("hasEquipment" + string, false);
				}
			} else {
				model.addAttribute("hasEquipment" + string, false);			
				}
		} else {
			model.addAttribute("hasEquipment" + string, false);
		}

	}

}
