package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.DietType;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.repository.DietRepository;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class DietService {

	private DietRepository dietRepository;
	private TrainingService trainingService;

	@Autowired
	public DietService(DietRepository dietRepository,TrainingService trainingService) {
		this.dietRepository = dietRepository;
		this.trainingService = trainingService;
	}
	
	@Transactional
	public void saveDiet(Diet diet, int trainingId) throws TrainingFinished{
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingFinished();
		else		
			dietRepository.save(diet);
	}
	
	@Transactional(readOnly=true)
	public Collection<Diet> findAllDiet() throws DataAccessException {
		return Lists.newArrayList(this.dietRepository.findAll());		
	}
	
	@Transactional(readOnly=true)
	public DietType selectDietType(Integer trainingId) {
		
		DietType res = null;
		Training t = this.trainingService.findTrainingById(trainingId);
		Collection<Routine> routines = t.getRoutines();
		Integer low = 0;
		Integer moderated = 0;
		Integer intense = 0;
		Integer veryIntense = 0;
		for(Routine r : routines) {
			Collection<RoutineLine> routineLine = r.getRoutineLine();
			for(RoutineLine rl : routineLine) {
				if(rl.getExercise().getIntensity().equals(Intensity.LOW))
					low++;
				if(rl.getExercise().getIntensity().equals(Intensity.MODERATED))
					moderated++;
				if(rl.getExercise().getIntensity().equals(Intensity.INTENSE))
					intense++;
				if(rl.getExercise().getIntensity().equals(Intensity.VERY_INTENSE))
					veryIntense++;
			}
		}
		if((low>moderated)&&(low>moderated)&&(low>moderated))
			res = DietType.VOLUME;
		if((moderated>=low)&&(moderated>=intense)&&(moderated>=veryIntense))
			res = DietType.MAINTENANCE;
		if((intense>low)&&(intense>moderated)&&(intense>veryIntense))
			res = DietType.DEFINITION;
		if((veryIntense>low)&&(veryIntense>intense)&&(veryIntense>moderated))
			res = DietType.EXTREME_DEFINITION;
		
		return res;
	}

	
	@Transactional(readOnly=true)
	public Diet findDietById(Integer dietId) throws DataAccessException {
		return this.dietRepository.findById(dietId).get();	
	}
}
