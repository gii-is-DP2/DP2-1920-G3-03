package org.springframework.samples.yogogym.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageClients;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageGuilds;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;
import org.springframework.samples.yogogym.service.DashboardsAdminService;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardsAdminController {

	private final DashboardsAdminService dashboardsAdminService;
	private final GuildService guildService;

	@Autowired
	public DashboardsAdminController(final DashboardsAdminService dashboardsAdminService, GuildService guildService) {
		this.dashboardsAdminService = dashboardsAdminService;
		this.guildService = guildService;
	}

	@GetMapping("/admin/dashboardEquipment")
	public String getDashboardEquipment(ModelMap model) {
		dashboardEquipment(28, "Month", model);
		dashboardEquipment(7, "Week", model);
		return "admin/dashboards/dashboardEquipment";
	}
	
	
	@GetMapping("/admin/dashboardChallenges/")
	public String getDashboardChallenges(@PathParam("monthAndYear") String monthAndYear, ModelMap model) {

		int date [] = getMonthAndYear(monthAndYear);
		int year = date[0];
		int month = date[1];
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		
		int numberOfChallenges = this.dashboardsAdminService.countChallengesOfMonthAndYear(month, year);
		if (numberOfChallenges > 0) {
			model.addAttribute("ChallengesExists", true);
			
			int countCompletedInscriptions = this.dashboardsAdminService.countCompletedInscriptionsOfMonthAndYear(month, year);
			if(countCompletedInscriptions == 0) {
				model.addAttribute("NoCompletedChallenges", true);
			}else {
				dashboardChallenges(month, year, model);
			}
		} else {
			model.addAttribute("ChallengesExists", false);
		}
		
		return "admin/dashboards/dashboardChallenges";
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
	
	
	/**
	 * <p>Get all the necessary data to create the dashboard.</p>
	 * @param days : number of days.
	 * @param string : 'Days' or 'Month'.
	 * @param model : the modelMap of the view.
	 * @return void : it puts the data in the model.
	 */
	private void dashboardEquipment(Integer days, String string, ModelMap model) {
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
	
	/**
	 * <p>Get all the necessary data to create the dashboard if there are challenges with inscriptions created</p>
	 * @param month
	 * @param year
	 * @param model: the modelMap of the view
	 * @return void: It puts the data in the model
	 */
	private void dashboardChallenges(int month, int year, ModelMap model) {
		
		// client with more points
		DashboardAdminChallengesTopClient p1 = this.dashboardsAdminService.getTopClient(month, year);
		String client = p1.getUsername();
		String email = p1.getEmail();
		Integer cPoints = p1.getPoints();

		model.addAttribute("client", client);
		model.addAttribute("cPoints", cPoints);
		model.addAttribute("email", email);

		// Guild with more points
		DashboardAdminChallengesTopGuild p2 = this.dashboardsAdminService.getTopGuild(month, year);
		String guild = p2.getGuild();
		Integer gPoints = p2.getPoints();

		model.addAttribute("guild", guild);
		model.addAttribute("gPoints", gPoints);

		// Graphs
		String[] challengesNames = this.dashboardsAdminService.getChallengesNamesOfMonthAndYear(month, year);
		List<DashboardAdminChallengesPercentageClients> p3 = this.dashboardsAdminService.getPercentageClients(month, year);
		List<DashboardAdminChallengesPercentageGuilds> p4 = this.dashboardsAdminService.getPercentageGuilds(month, year);
		
		getPercentagesAndNames(p3, p4, challengesNames, model);
	}

	/**
	 * <p>Merge all the challenges names with its corresponding percentages</p>
	 * @param p1: DashboardAdminChallengesPercentageClients
	 * @param p2: DashboardAdminChallengesPercentageGuilds
	 * @param String[]: challengesNames
	 * @param model: the modelMap of the view
	 * @return void: It puts the data in the model
	 */
	private void getPercentagesAndNames(List<DashboardAdminChallengesPercentageClients> p1,
										List<DashboardAdminChallengesPercentageGuilds> p2, String[] challengesNames, ModelMap model) {
		
		int numberOfChallenges = challengesNames.length;
		long[] percentageClients = new long[numberOfChallenges];
		long[] percentageGuilds = new long[numberOfChallenges];
		
		
		int j = 0;
		int k = 0;
		for(int i = 0; i < numberOfChallenges; i++) {
			
			if(j < p1.size() && challengesNames[i].equals(p1.get(j).getChallengeName())) {
				percentageClients[i] = Math.round(p1.get(j).getPercentageClients() *100);
				j++;
				
				if(k < p2.size() && challengesNames[i].equals(p2.get(k).getChallengeName())) {
					percentageGuilds[i] = Math.round(p2.get(k).getPercentageGuilds() *100);
					k++;
				}
				else {
					percentageGuilds[i] = 0;
				}
			}
			else {
				percentageClients[i] = 0;
				percentageGuilds[i] = 0;
			}
		}
		model.addAttribute("challengesNames", challengesNames);
		model.addAttribute("percentageClients", percentageClients);
		model.addAttribute("percentageGuilds", percentageGuilds);
	}
	
}
