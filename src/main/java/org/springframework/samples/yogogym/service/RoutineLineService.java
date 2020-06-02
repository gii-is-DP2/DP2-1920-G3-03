package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.RoutineLineRepository;
import org.springframework.samples.yogogym.service.exceptions.ExerciseNotCorrectRepetitionType;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.samples.yogogym.service.exceptions.RoutineLineRepAndTimeSetted;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoutineLineService {

	private RoutineLineRepository routineLineRepository;
	private TrainingService trainingService;

	@Autowired
	public RoutineLineService(RoutineLineRepository routineLineRepository, TrainingService trainingService) {
		this.routineLineRepository = routineLineRepository;
		this.trainingService = trainingService;
	}
	
	@Transactional(readOnly=true)
	public RoutineLine findRoutineLineById(int routineLineId) throws DataAccessException {
		return this.routineLineRepository.findRoutineLineById(routineLineId);
	}
	
	@Transactional(readOnly=true)
	public Collection<RoutineLine> findAllRoutinesLines() throws DataAccessException
	{
		return this.routineLineRepository.findAllRoutines();
	}
	
	@Transactional(rollbackFor= {TrainingFinished.class})
	public void deleteRoutineLine(RoutineLine routineLine,int trainingId) throws DataAccessException, TrainingFinished {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingFinished();
		else
			this.routineLineRepository.delete(routineLine);
	}
	
	@Transactional(rollbackFor= {TrainingFinished.class, RoutineLineRepAndTimeSetted.class})
	public void saveRoutineLine(RoutineLine routineLine, int trainingId) throws DataAccessException,TrainingFinished, RoutineLineRepAndTimeSetted, ExerciseNotCorrectRepetitionType {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingFinished();
		else if(routineLine.getTime() != null && routineLine.getReps() != null)
			throw new RoutineLineRepAndTimeSetted();
		else if(!isCorrectRepetitionType(routineLine))
			throw new ExerciseNotCorrectRepetitionType();
		else
			this.routineLineRepository.save(routineLine);
	}
	
	//Derivative method

	protected Boolean isCorrectRepetitionType(RoutineLine routineLine)
	{
		Boolean res = true;
		
		switch(routineLine.getExercise().getRepetitionType())
		{
			case REPS:				
				if(routineLine.getTime() != null)
					return false;				
				break;
			case TIME:				
				if(routineLine.getReps() != null)
					return false;				
				break;
			case TIME_AND_REPS:				
				return res;
		}		
		
		return res;
	}
}
