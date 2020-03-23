package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClasificationController {

	private InscriptionService inscriptionService;

	private ClientService clientService;

	@Autowired
	public ClasificationController(InscriptionService inscriptionService, ClientService clientService) {
		this.inscriptionService = inscriptionService;
		this.clientService = clientService;
	}

	@GetMapping("/client/{clientUsername}/clasification")
	public String getClasification(@PathVariable("clientUsername") String clientUsername, Model model) {
		if (isTheRealUser(clientUsername)) {
			List<Inscription> listInscription = this.inscriptionService.findInscriptionsByUsername(clientUsername);
			List<Client> listClientWithPoint = this.clientService.findClientsWithCompletedInscriptions();
			if (!listClientWithPoint.isEmpty()) {
				// Table and total points of client
				if (!listInscription.isEmpty()) {
					List<Challenge> listChallenge = new ArrayList<Challenge>();
					Integer totalPoint = 0;
					for (Inscription x : listInscription) {
						if (x != null) {
							if (x.getChallenge() != null && x.getStatus().equals(Status.COMPLETED)) {
								listChallenge.add(x.getChallenge());
								totalPoint += x.getChallenge().getPoints();
							}
						}
					}
					if (!listChallenge.isEmpty()) {
						model.addAttribute("hasChallenge", true);
						model.addAttribute("challenges", listChallenge);
						model.addAttribute("totalPoint", totalPoint);
					} else {
						model.addAttribute("hasChallenge", false);
					}
				} else {
					model.addAttribute("hasChallenge", false);
				}
				// Clasification
				dashboardClasification(listClientWithPoint, clientUsername, listInscription, model, "Week");
				dashboardClasification(listClientWithPoint, clientUsername, listInscription, model, "All");
			} else {
				model.addAttribute("hasChallenge", false);
				model.addAttribute("hasChallengeClasification", false);
			}
			return "client/clasifications/clasification";
		}else {
			return "exception";
		}
	}

	private void dashboardClasification(List<Client> listClientWithPoint, String clientUsername,
			List<Inscription> listInscription, Model model, String days) {
		Map<Integer, String> mapClientPoint = new TreeMap<Integer, String>();
		Date d1 = new Date();
		Date d2 = new Date();
		if (days.equals("Week")) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, -7);
			d2 = now.getTime();
		}
		for (Client x : listClientWithPoint) {
			Integer aux = 0;
			for (Inscription y : x.getInscriptions()) {
				if (y != null && y.getStatus().equals(Status.COMPLETED)) {
					if (days.equals("Week") && y.getChallenge().getInitialDate().after(d2)
							&& y.getChallenge().getInitialDate().before(d1)) {
						aux += y.getChallenge().getPoints();
					} else if (days.equals("All")) {
						aux += y.getChallenge().getPoints();
					}
				}
			}
			if (aux != 0) {
				mapClientPoint.put(aux, x.getUser().getUsername());
			}
		}
		if (!mapClientPoint.isEmpty()) {
			Integer[] orderPoint = new Integer[mapClientPoint.size()];
			String[] orderUser = new String[mapClientPoint.size()];
			List<Integer> aux = new ArrayList<Integer>(mapClientPoint.keySet());
			List<String> position = new ArrayList<String>();
			for (int i = mapClientPoint.size(); i > 0; i = i - 1) {
				orderPoint[mapClientPoint.size() - i] = aux.get(i - 1);
				orderUser[mapClientPoint.size() - i] = mapClientPoint.get(aux.get(i - 1));
				position.add(mapClientPoint.get(aux.get(i - 1)));
			}
			if (!listInscription.isEmpty()) {
				if (position.contains(clientUsername)) {
					Integer pos = position.indexOf(clientUsername) + 1;
					model.addAttribute("position" + days, pos);
					model.addAttribute("hasPosition" + days, true);
				}
			}
			model.addAttribute("hasChallengeClasification" + days, true);
			model.addAttribute("orderPoint" + days, orderPoint);
			model.addAttribute("orderUser" + days, orderUser);
		} else {
			model.addAttribute("hasChallengeClasification" + days, false);
		}
	}

	private boolean isTheRealUser(String usernameClient) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username.equals(usernameClient);
	}

}
