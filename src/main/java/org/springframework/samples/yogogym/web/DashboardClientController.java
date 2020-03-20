package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.ClientService;
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
	private final ClientService clientService;

	@Autowired
	public DashboardClientController(final DashboardClientService dashboardClientService,
			final ClientService clientService) {
		this.dashboardClientService = dashboardClientService;
		this.clientService = clientService;
	}

	@GetMapping("/client/{usernameClient}/dashboard")
	public String getDashboard(@PathVariable("usernameClient") String usernameClient, Model model) {
		if (isTheRealUser(usernameClient)) {
			Client client = this.clientService.findClientByUsername(usernameClient);
			if (client != null) {
				List<Training> listTraining = this.dashboardClientService.listTrainingByClient(client.getId());
				if (!listTraining.isEmpty()) {
					dashboard(listTraining, 28, "Month", model);
					dashboard(listTraining, null, "All", model);
				} else {
					model.addAttribute("hasExerciseMonth", false);
					model.addAttribute("hasExerciseAll", false);
				}
			} else {
				model.addAttribute("hasExerciseMonth", false);
				model.addAttribute("hasExerciseAll", false);
			}
			return "client/dashboards/dashboard";
		}else {
			return "exception";
		}
	}

	private void dashboard(List<Training> listTraining, Integer days, String string, Model model) {
		List<Integer> listTrainingFilter = new ArrayList<>();
		if (days != null) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, -days);
			Date d2 = now.getTime();
			for (Training t : listTraining) {
				if (t.getInitialDate().before(new Date()) && t.getInitialDate().after(d2)) {
					listTrainingFilter.add(t.getId());
				}
			}
		} else {
			for (Training t : listTraining) {
				listTrainingFilter.add(t.getId());
			}
		}
		if (listTrainingFilter.isEmpty()) {
			model.addAttribute("hasExercise" + string, false);
		} else {
			List<Integer> listRoutine = new ArrayList<>();
			for (Integer x : listTrainingFilter) {
				listRoutine.addAll(this.dashboardClientService.listRoutineByTraining(x));
			}
			List<Integer> listRepsRoutine = new ArrayList<>();
			for (Integer x : listRoutine) {
				if (x != null) {
					Integer aux = this.dashboardClientService.listRepsRoutineByRoutine(x);
					if (aux != null && aux != 0) {
						for (int i = 0; i < aux; i++) {
							listRepsRoutine.add(x);
						}
					}
				}
			}
			if (!listRepsRoutine.isEmpty()) {
				List<Exercise> listExercise = new ArrayList<>();
				for (Integer x : listRepsRoutine) {
					listExercise.addAll(this.dashboardClientService.listExerciseByRoutine(x));
				}
				if (!listExercise.isEmpty()) {
					Integer sumKcal = 0;
					List<BodyParts> listBodyParts = new ArrayList<BodyParts>();
					List<Integer> listCountBodyParts = new ArrayList<Integer>();
					List<RepetitionType> listRepetitionType = new ArrayList<RepetitionType>();
					List<Integer> listCountRepetitionType = new ArrayList<Integer>();
					for (Exercise x : listExercise) {
						if (x.getBodyPart() != null)
							listBodyParts.add(x.getBodyPart());
						if (x.getRepetitionType() != null)
							listRepetitionType.add(x.getRepetitionType());
						if (x.getKcal() != null)
							sumKcal += x.getKcal();
					}
					Set<BodyParts> aux = new HashSet<>(listBodyParts);
					List<BodyParts> orderBodyParts = new ArrayList<>();
					orderBodyParts.addAll(aux);
					for (BodyParts b : orderBodyParts) {
						listCountBodyParts.add(Collections.frequency(listBodyParts, b));
					}
					Set<RepetitionType> aux2 = new HashSet<>(listRepetitionType);
					List<RepetitionType> orderRepetitionType = new ArrayList<>();
					orderRepetitionType.addAll(aux2);
					for (RepetitionType r : orderRepetitionType) {
						listCountRepetitionType.add(Collections.frequency(listRepetitionType, r));
					}
					String[] sBodyParts = new String[orderBodyParts.size()];
					Integer[] cBodyParts = new Integer[listCountBodyParts.size()];
					for (int i = 0; i < orderBodyParts.size(); i++) {
						sBodyParts[i] = orderBodyParts.get(i).name();
						cBodyParts[i] = listCountBodyParts.get(i);
					}
					String[] sRepetitionType = new String[orderRepetitionType.size()];
					Integer[] cRepetitionType = new Integer[listCountRepetitionType.size()];
					for (int i = 0; i < orderRepetitionType.size(); i++) {
						sRepetitionType[i] = orderRepetitionType.get(i).name();
						cRepetitionType[i] = listCountRepetitionType.get(i);
					}
					model.addAttribute("kcal" + string, sumKcal);
					model.addAttribute("orderBodyParts" + string, sBodyParts);
					model.addAttribute("countBodyParts" + string, cBodyParts);
					model.addAttribute("orderRepetitionType" + string, sRepetitionType);
					model.addAttribute("countRepetitionType" + string, cRepetitionType);
					model.addAttribute("hasExercise" + string, true);
				}else {
					model.addAttribute("hasExercise" + string, false);
				}
			} else {
				model.addAttribute("hasExercise" + string, false);
			}
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
