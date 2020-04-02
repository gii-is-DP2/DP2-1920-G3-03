package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Training;
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
		Collection<Training> listTraining = this.dashboardsAdminService.equipmentControl();
		if (!listTraining.isEmpty()) {
			dashboard(listTraining, 28, "Month", model);
			dashboard(listTraining, 7, "Week", model);
		} else {
			model.addAttribute("hasEquipmentMonth", false);
			model.addAttribute("hasEquipmentWeek", false);
		}
		return "admin/dashboards/dashboardEquipment";
	}
	
	@GetMapping("/admin/dashboardChallenges")
	public String getDashboardChallenges(Model model) {
		
		model.addAttribute("test",true);
		return "admin/dashboards/dashboardChallenges";
	}

	private void dashboard(Collection<Training> listTraining, Integer days, String string, Model model) {
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
