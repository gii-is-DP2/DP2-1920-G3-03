package org.springframework.samples.yogogym.web;

import java.time.Instant;
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
import org.springframework.web.bind.annotation.PostMapping;


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
		List<Integer> listTrainingFilter = new ArrayList<>();
		if(!listTraining.isEmpty()) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, -28);
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
				listExercise.add(this.dashboardService.listExercise(x));
			}
			List<Integer> listIdEquipment = new ArrayList<>();
			for(Integer x : listExercise) {
				Integer aux = this.dashboardService.listIdEquipment(x);
				if(aux!=null) {
					listIdEquipment.add(aux);
				}
			}
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
			model.addAttribute("orderName", s);
			model.addAttribute("count", c);
		}else {
			
		}
		return "admin/dashboards/dashboardEquipment";
	}

}
