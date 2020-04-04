package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.DashboardsAdminRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardsAdminService {
	
	@Autowired
	private DashboardsAdminRepository dashboardRepository;
	
	/* Equipment control */
	
	public Collection<Training> equipmentControl(){
		return this.dashboardRepository.findIdTrainingByDate();
	}
	
	public List<Integer> listRoutine(Integer training){
		return this.dashboardRepository.findIdRoutineByIdTraining(training);
	}
	
	public Integer listRepsRoutine(Integer routine){
		return this.dashboardRepository.findRepsPerWeekByIdRoutine(routine);
	}
	
	public List<Integer> listExercise(Integer routine){
		return this.dashboardRepository.findIdExerciseByIdRoutine(routine);
	}
	
	public Integer listIdEquipment (Integer exercise){
		return this.dashboardRepository.findIdEquipmentByIdExercise(exercise);
	}
	
	public String listNameEquipment (Integer equipment){
		return this.dashboardRepository.findNameEquipmentByIdEquipment(equipment);
	}

	public Collection<Challenge> getChallengesOfMonth(Integer month) {

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		Integer year = cal.get(Calendar.YEAR);
		
		return this.dashboardRepository.findChallengesByMonthAndYear(month,year);
	}

}
