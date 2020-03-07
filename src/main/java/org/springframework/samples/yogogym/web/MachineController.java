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

@Controller
public class MachineController {
		
	private final MachineService machineService;

	@Autowired
	public MachineController(MachineService machineService) {
		this.machineService = machineService;
	}	

}
