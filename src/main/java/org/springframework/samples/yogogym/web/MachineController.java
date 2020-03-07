package org.springframework.samples.yogogym.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Machine;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MachineController {
		
	private final MachineService machineService;

	@Autowired
	public MachineController(MachineService machineService) {
		this.machineService = machineService;
	}	

	@GetMapping("/mainMenu/machines")
	public String findAllMachine(Model model) 
	{	  
		//Find Machines
	   	Collection<Machine> machineCollection = this.machineService.findAllMachine();
	   	model.addAttribute("machines",machineCollection);
	   	
	    return "mainMenu/machines/machinesList";
	}
	
	@GetMapping("/mainMenu/machines/{machineId}")
	public String findMachineDetailById(@PathVariable("machineId") int id,Model model) 
	{	  
		//Find Machine by Id
	   	Machine machine = this.machineService.findMachineByExerciseId(id);
	   	model.addAttribute("machine",machine);
	   	
	    return "mainMenu/machines/machinesDetails";
	}

}
