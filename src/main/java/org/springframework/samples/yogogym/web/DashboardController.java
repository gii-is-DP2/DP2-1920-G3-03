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
import org.springframework.samples.yogogym.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {
	
	private final DashboardService dashboardService;
	
	@Autowired
	public DashboardController(final DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}
	
	@GetMapping("/admin/dashboard")
	public String getDashboard(Model model) {
		Collection<Training> listTraining = this.dashboardService.equipmentControl();
		if(!listTraining.isEmpty()) {
			dashboard(listTraining, 28, "Month", model);
			dashboard(listTraining, 7, "Week", model);
		}else {
			String[] s = {""};
			Integer[] c = {};
			model.addAttribute("orderNameMonth", s);
			model.addAttribute("countMonth", c);
			model.addAttribute("orderNameWeek", s);
			model.addAttribute("countWeek", c);
			model.addAttribute("noEquipment", true);
		}
		return "admin/dashboards/dashboardEquipment";
	}

	private void dashboard(Collection<Training> listTraining, Integer days, String string, Model model) {
		List<Integer> listTrainingFilter = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -days);
		Date d2 = now.getTime();
		for(Training t : listTraining) {
			if(t.getInitialDate().before(new Date()) && t.getInitialDate().after(d2)) {
				listTrainingFilter.add(t.getId());
			}
		}
		List<Integer> listRoutine = new ArrayList<>();
		for(Integer x : listTrainingFilter) {
			listRoutine.addAll(this.dashboardService.listRoutine(x));
		}
		List<Integer> listRepsRoutine = new ArrayList<>();
		for(Integer x : listRoutine) {
			Integer aux = this.dashboardService.listRepsRoutine(x);
			if(aux!=null && aux!=0) {
				for(int i=0;i<aux;i++) {
					listRepsRoutine.add(x);
				}
			}
		}
		List<Integer> listExercise = new ArrayList<>();
		for(Integer x : listRepsRoutine) {
			listExercise.addAll(this.dashboardService.listExercise(x));
		}
		List<Integer> listIdEquipment = new ArrayList<>();
		for(Integer x : listExercise) {
			Integer aux = this.dashboardService.listIdEquipment(x);
			if(aux!=null) {
				listIdEquipment.add(aux);
			}
		}
		if(!listIdEquipment.isEmpty()) {
			List<String> listNameEquipment = new ArrayList<>();
			for(Integer x : listIdEquipment) {
				listNameEquipment.add(this.dashboardService.listNameEquipment(x));
			}
			Set<String> aux = new HashSet<>(listNameEquipment);
			List<String> orderName = new ArrayList<>();
			orderName.addAll(aux);
			List<Integer> count = new ArrayList<>();
			for(String s : orderName) {
				count.add(Collections.frequency(listNameEquipment, s));
			}
			String[] s = new String[orderName.size()];
			for(int i = 0;i<orderName.size();i++) {
				s[i] = orderName.get(i);
			}
			Integer[] c = new Integer[count.size()];
			for(int i = 0;i<count.size();i++) {
				c[i] = count.get(i);
			}
			model.addAttribute("orderName" + string, s);
			model.addAttribute("count" + string, c);
		}else {
			String[] s = {""};
			Integer[] c = {};
			model.addAttribute("orderName" + string, s);
			model.addAttribute("count" + string, c);
			model.addAttribute("noEquipment", true);
		}
	}

}
