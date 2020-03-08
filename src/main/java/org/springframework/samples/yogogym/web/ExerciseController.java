
package org.springframework.samples.yogogym.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExerciseController {

	private final ExerciseService exerciseService;


	@Autowired
	public ExerciseController(final ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@GetMapping("/mainMenu/exercises")
	public String findAllExercise(final Model model) {
		//Find Exercises
		Collection<Exercise> exerciseCollection = this.exerciseService.findAllExercise();
		model.addAttribute("exercises", exerciseCollection);

		return "mainMenu/exercises/exercisesList";
	}

	@GetMapping("/mainMenu/exercises/{exerciseId}")
	public ModelAndView showExercise(@PathVariable("exerciseId") final int exerciseId) {
		ModelAndView mav = new ModelAndView("mainMenu/exercises/exerciseDetails");
		mav.addObject(this.exerciseService.findExerciseById(exerciseId));
		return mav;
	}

}
