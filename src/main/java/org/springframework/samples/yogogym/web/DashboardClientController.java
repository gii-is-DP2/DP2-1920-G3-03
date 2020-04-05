package org.springframework.samples.yogogym.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.service.DashboardClientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardClientController {

	private final DashboardClientService dashboardClientService;

	@Autowired
	public DashboardClientController(final DashboardClientService dashboardClientService) {
		this.dashboardClientService = dashboardClientService;

	}

	@GetMapping("/client/{usernameClient}/dashboard")
	public String getDashboard(@PathVariable("usernameClient") String usernameClient, Model model) {
		if (isTheRealUser(usernameClient)) {
			dashboard(usernameClient, 28, "Month", model);
			dashboard(usernameClient, null, "All", model);
			return "client/dashboards/dashboard";
		} else {
			return "exception";
		}
	}

	private void dashboard(String username, Integer days, String string, Model model) {
		List<Integer> countBodyPart = this.dashboardClientService.countBodyPart(days, username);
		List<String> nameBodyPart = this.dashboardClientService.nameBodyPart(days, username);
		List<Integer> countRepetitionType = this.dashboardClientService.countRepetitionType(days, username);
		List<String> nameRepetitionType = this.dashboardClientService.nameRepetitionType(days, username);
		Integer kcal = this.dashboardClientService.sumKcal(days, username);
		if (!countBodyPart.isEmpty() && !nameBodyPart.isEmpty()) {
			String[] sBodyParts = new String[nameBodyPart.size()];
			Integer[] cBodyParts = new Integer[countBodyPart.size()];
			for (int i = 0; i < nameBodyPart.size(); i++) {
				sBodyParts[i] = nameBodyPart.get(i);
				cBodyParts[i] = countBodyPart.get(i);
			}
			model.addAttribute("orderBodyParts" + string, sBodyParts);
			model.addAttribute("countBodyParts" + string, cBodyParts);
			model.addAttribute("hasBodyParts" + string, true);
		} else {
			model.addAttribute("hasBodyParts" + string, false);
		}
		if (!countRepetitionType.isEmpty() && !nameRepetitionType.isEmpty()) {
			String[] sRepetitionType = new String[nameRepetitionType.size()];
			Integer[] cRepetitionType = new Integer[countRepetitionType.size()];
			for (int i = 0; i < nameRepetitionType.size(); i++) {
				sRepetitionType[i] = nameRepetitionType.get(i);
				cRepetitionType[i] = countRepetitionType.get(i);
			}
			model.addAttribute("orderRepetitionType" + string, sRepetitionType);
			model.addAttribute("countRepetitionType" + string, cRepetitionType);
			model.addAttribute("hasRepetitionType" + string, true);
		} else {
			model.addAttribute("hasRepetitionType" + string, false);
		}
		if (kcal != null) {
			model.addAttribute("kcal" + string, kcal);
			model.addAttribute("hasKcal" + string, true);
		} else {
			model.addAttribute("hasKcal" + string, false);
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
