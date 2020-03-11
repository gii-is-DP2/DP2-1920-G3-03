package org.springframework.samples.yogogym.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.samples.yogogym.repository.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	
	private DashboardRepository dashboardRepository;
	
	/* Equipment control */
	
	public List<String> equipmentControl(Date initialDate, Date endDate){
		
		List<Integer> listTraining = this.dashboardRepository.findIdTrainingByDate(initialDate, endDate).isEmpty()? new ArrayList<>() : this.dashboardRepository.findIdTrainingByDate(initialDate, endDate);
		if(!listTraining.isEmpty()) {
			List<Integer> listRoutine = new ArrayList<>();
			for(Integer x : listTraining) {
				listRoutine.addAll(this.dashboardRepository.findIdRoutineByIdTraining(x));
			}
			List<Integer> listRepsRoutine = new ArrayList<>();
			for(Integer x : listRoutine) {
				Integer aux = this.dashboardRepository.findRepsPerWeekByIdRoutine(x);
				if(aux!=null && aux!=0) {
					for(int i=0;i<aux;i++) {
						listRepsRoutine.add(x);
					}
				}
			}
			List<Integer> listExercise = new ArrayList<>();
			for(Integer x : listRepsRoutine) {
				listExercise.add(this.dashboardRepository.findIdExerciseByIdRoutine(x));
			}
			List<Integer> listIdEquipment = new ArrayList<>();
			for(Integer x : listExercise) {
				Integer aux = this.dashboardRepository.findIdEquipmentByIdExercise(x);
				if(aux!=null) {
					listIdEquipment.add(aux);
				}
			}
			List<String> listNameEquipment = new ArrayList<>();
			for(Integer x : listIdEquipment) {
				listNameEquipment.add(this.dashboardRepository.findNameEquipmentByIdEquipment(x));
			}
			return listNameEquipment;
		}else{
			return new ArrayList<>();
		}
	}

}
