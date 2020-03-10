package org.springframework.samples.yogogym.web;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

	@Autowired
	private ExerciseService exerciseService;
	
	
	@GetMapping(value = "/{exerciseId}")
	public String showExercise(@PathVariable("exerciseId") int exerciseId,ModelMap modelMap) {
		
		String vista = "exercises/exerciseDetails";
		
		Optional<Exercise> e = exerciseService.findById(exerciseId);
		if(e.isPresent()) {
			modelMap.addAttribute("exercise", e.get());
		}
		else {
			modelMap.addAttribute("message", "Exercise Not Found");
		}
		
		
		return vista;
	}
	
}
