package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClasificationController {

	private InscriptionService inscriptionService;

	@Autowired
	public ClasificationController(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}

	@GetMapping("/client/{clientUsername}/clasification")
	public String getClasification(@PathVariable("clientUsername") String clientUsername, Model model) {
		List<Inscription> listInscription = this.inscriptionService.findInscriptionsByUsername(clientUsername);
		if (!listInscription.isEmpty()) {
			List<Challenge> listChallenge = new ArrayList<Challenge>();
			for (Inscription x : listInscription) {
				if (x != null) {
					if (x.getChallenge().getName() != null)
						listChallenge.add(x.getChallenge());
				}
			}
			if (!listChallenge.isEmpty()) {
				model.addAttribute("hasChallenge", true);
				model.addAttribute("challenges", listChallenge);
			} else {
				model.addAttribute("hasChallenge", false);
			}
		} else {
			model.addAttribute("hasChallenge", false);
		}
		return "client/clasifications/clasification";
	}

}
