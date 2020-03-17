package org.springframework.samples.yogogym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.DashboardClientRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardClientService {
	
	@Autowired
	private DashboardClientRepository dashboardClientRepository;

	public List<Training> listTrainingByClient(Integer idClient){
		return this.dashboardClientRepository.findIdTrainingByClient(idClient);
	}
	
	public List<Integer> listRoutineByTraining(Integer idTraining){
		return this.dashboardClientRepository.findIdRoutineByIdTraining(idTraining);
	}
	
	public Integer listRepsRoutineByRoutine(Integer idRoutine){
		return this.dashboardClientRepository.findRepsPerWeekByIdRoutine(idRoutine);
	}
	
	public List<Exercise> listExerciseByRoutine(Integer idRoutine){
		return this.dashboardClientRepository.findIdExerciseByIdRoutine(idRoutine);
	}

}
