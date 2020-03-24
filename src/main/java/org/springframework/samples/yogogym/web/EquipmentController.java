package org.springframework.samples.yogogym.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.service.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EquipmentController {
		
	private final EquipmentService equipmentService;

	@Autowired
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}	

	@GetMapping("/mainMenu/equipments")
	public String findAllEquipment(Model model) 
	{	  
		//Find equipments
	   	Collection<Equipment> equipmentCollection = this.equipmentService.findAllEquipment();
	   	model.addAttribute("equipments",equipmentCollection);
	   	
	    return "mainMenu/equipments/equipmentsList";
	}
	
	@GetMapping("/mainMenu/equipments/{equipmentId}")
	public String findEquipmentDetailById(@PathVariable("equipmentId") int id,Model model) 
	{	  
		//Find equipment by Id
	   	Equipment equipment = this.equipmentService.findEquipmentById(id);
	   	model.addAttribute("equipment",equipment);
	   	
	    return "mainMenu/equipments/equipmentsDetails";
	}

}
