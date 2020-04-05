package org.springframework.samples.yogogym.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.service.DashboardsAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardsAdminController {

	private final DashboardsAdminService dashboardsAdminService;

	@Autowired
	public DashboardsAdminController(final DashboardsAdminService dashboardsAdminService) {
		this.dashboardsAdminService = dashboardsAdminService;
	}

	@GetMapping("/admin/dashboardEquipment")
	public String getDashboardEquipment(Model model) {
		dashboard(28, "Month", model);
		dashboard(7, "Week", model);
		return "admin/dashboards/dashboardEquipment";
	}

	@GetMapping("/admin/dashboardChallenges")
	public String getDashboardChallenges(Model model) {

		model.addAttribute("test", true);
		return "admin/dashboards/dashboardChallenges";
	}

	private void dashboard(Integer days, String string, Model model) {
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

}
