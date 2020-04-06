package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClasificationController {

	private ChallengeService challengeService;

	private ClientService clientService;

	@Autowired
	public ClasificationController(final ChallengeService challengeService, final ClientService clientService) {
		this.challengeService = challengeService;
		this.clientService = clientService;
	}

	@GetMapping("/client/{clientUsername}/clasification")
	public String getClasification(@PathVariable("clientUsername") String clientUsername, Model model) {
		if (isTheRealUser(clientUsername)) {
			List<Challenge> listChallenge = this.challengeService.findChallengesByUsername(clientUsername);
			Integer totalPoint = this.challengeService.sumPointChallengesByUsername(clientUsername);
			if (!listChallenge.isEmpty() && totalPoint != null) {
				model.addAttribute("hasChallenge", true);
				model.addAttribute("challenges", listChallenge);
				model.addAttribute("totalPoint", totalPoint);

			} else {
				model.addAttribute("hasChallenge", false);
			}
			// Clasification
			dashboardClasification(clientUsername, model, "Week");
			dashboardClasification(clientUsername, model, "All");
			return "client/clasifications/clasification";
		} else {
			return "exception";
		}
	}

	private void dashboardClasification(String clientUsername, Model model, String days) {
		List<String> orderNameList = new ArrayList<String>();
		List<Integer> orderPointList = new ArrayList<Integer>();
		if (days.equals("Week")) {
			orderNameList = this.clientService.classificationNameDate();
			orderPointList = this.clientService.classificationPointDate();
		} else {
			orderNameList = this.clientService.classificationNameAll();
			orderPointList = this.clientService.classificationPointAll();
		}
		if (!orderNameList.isEmpty() && !orderPointList.isEmpty()) {
			Integer[] orderPoint = new Integer[orderPointList.size()];
			String[] orderUser = new String[orderNameList.size()];
			for (int i = 0; i < orderNameList.size(); i++) {
				orderPoint[i] = orderPointList.get(i);
				orderUser[i] = orderNameList.get(i);
			}
			if (orderNameList.contains(clientUsername)) {
				Integer pos = orderNameList.indexOf(clientUsername) + 1;
				model.addAttribute("position" + days, pos);
				model.addAttribute("hasPosition" + days, true);
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
