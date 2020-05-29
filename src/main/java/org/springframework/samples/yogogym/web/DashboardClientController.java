package org.springframework.samples.yogogym.web;

import java.util.Calendar;
import java.util.Date;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.service.DashboardClientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String getDashboard(@PathParam("monthAndYear") String monthAndYear, @PathVariable("usernameClient") String usernameClient, ModelMap model) {
		if (isTheRealUser(usernameClient)) {
			int date [] = getMonthAndYear(monthAndYear);
			int year = date[0];
			int month = date[1];
			model.addAttribute("year",year);
			model.addAttribute("month",month);
			model.addAttribute("usernameClient", usernameClient);
			dashboard(usernameClient, month, year, model);
			return "client/dashboards/dashboard";
		} else {
			return "exception";
		}
	}

	private void dashboard(String username, int month, int year, ModelMap model) {
		Integer[] countBodyPart = this.dashboardClientService.countBodyPart(month, year, username);
		String[] nameBodyPart = this.dashboardClientService.nameBodyPart(month, year, username);
		Integer[] countRepetitionType = this.dashboardClientService.countRepetitionType(month, year, username);
		String[] nameRepetitionType = this.dashboardClientService.nameRepetitionType(month, year, username);
		Integer kcal = this.dashboardClientService.sumKcal(month, year, username);
		if (countBodyPart.length>0 && nameBodyPart.length>0) {
			model.addAttribute("orderBodyParts", nameBodyPart);
			model.addAttribute("countBodyParts", countBodyPart);
			model.addAttribute("hasBodyParts", true);
		} else {
			model.addAttribute("hasBodyParts", false);
		}
		if (countRepetitionType.length>0 && nameRepetitionType.length>0) {
			model.addAttribute("orderRepetitionType", nameRepetitionType);
			model.addAttribute("countRepetitionType", countRepetitionType);
			model.addAttribute("hasRepetitionType", true);
		} else {
			model.addAttribute("hasRepetitionType", false);
		}
		if (kcal != null) {
			model.addAttribute("kcal", kcal);
			model.addAttribute("hasKcal", true);
		} else {
			model.addAttribute("hasKcal", false);
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
	
	/**
	 * <p>Separate the Month and Year from a String</p>
	 * @param monthAndYear: A string of this type YYYY-mm
	 * @return int[]: An array of: [0]: the Year  [1]: The Month
	 */
	private int[] getMonthAndYear(String monthAndYear) {
		int date [] = {0,0};
		
		if(monthAndYear == null) {
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			
			date[0] = cal.get(Calendar.YEAR);
			date[1] = cal.get(Calendar.MONTH) + 1;
		}
		else {
			String[] str =  monthAndYear.split("-");
			date[0] = Integer.valueOf(str[0]);
			date[1] = Integer.valueOf(str[1]);
		}
		return date;
	}

}
