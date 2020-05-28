package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.repository.RoutineRepository;
import org.springframework.samples.yogogym.service.exceptions.MaxRoutinesException;
import org.springframework.samples.yogogym.service.exceptions.NotEditableException;
import org.springframework.samples.yogogym.service.exceptions.RoutineRepsPerWeekNotValid;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class RoutineService {

	private RoutineRepository routineRepository;
	private TrainingService trainingService;
	private AuthoritiesService authService;
	
	final int MAX_ROUTINES = 10;
	
	@Autowired
	public RoutineService(RoutineRepository routineRepository, TrainingService trainingService, AuthoritiesService authService) {
		this.routineRepository = routineRepository;
		this.trainingService = trainingService;
		this.authService = authService;
	}
	
	
	@Transactional(rollbackFor= {TrainingFinished.class})
	public void saveRoutine(Routine routine, String username, int trainingId) throws DataAccessException, TrainingFinished, NotEditableException, MaxRoutinesException, RoutineRepsPerWeekNotValid {
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingFinished();
		else if(CheckMaxRoutinesFromTraining(training))
			throw new MaxRoutinesException();
		else if(!CheckEditable(username,training))
			throw new NotEditableException();
		else if(!validRepsPerWeekValue(routine,1,20))
			throw new RoutineRepsPerWeekNotValid();
		else
			routineRepository.save(routine);
	}
	
	@Transactional(rollbackFor= {TrainingFinished.class})
	public void deleteRoutine(Routine routine, String username, int trainingId) throws DataAccessException, TrainingFinished, NotEditableException {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingFinished();
		else if(!CheckEditable(username,training))
			throw new NotEditableException();
		else
			routineRepository.delete(routine);
	}
	
	@Transactional(readOnly=true)
	public Collection<Routine> findAllRoutines() throws DataAccessException {
		return Lists.newArrayList(routineRepository.findAll());
	}
	
	@Transactional(readOnly=true)
	public Collection<Routine> findAllRoutinesFromTraining(int id) throws DataAccessException {
		return routineRepository.findAllRoutinesFromTraining(id);
	}
	
	@Transactional(readOnly=true)
	public Routine findRoutineById(int routineId) throws DataAccessException {
		return this.routineRepository.findRoutineById(routineId);
	}
	
	//Derivative method
	
	protected Boolean isAuthority(String username, String authority)
	{
		return this.authService.findAuthByUsername(username).stream().map(x->x.getAuthority()).collect(Collectors.toList()).contains(authority);
	}
	
	protected Boolean CheckEditable(String username, Training training)
	{
		if(training.getEditingPermission() == EditingPermission.CLIENT)
		{
			return isAuthority(username,"client");
		}
		else if(training.getEditingPermission() == EditingPermission.TRAINER)
		{
			return isAuthority(username,"trainer");
		}
		else
		{
			return true;
		}
	}
	
	protected Boolean CheckMaxRoutinesFromTraining(final Training training)
	{
		if(training.getRoutines().size() >= MAX_ROUTINES)
			return true;
		
		return false;
	}
	
	protected Boolean validRepsPerWeekValue(final Routine routine, int min, int max)
	{
		int num = routine.getRepsPerWeek();
		
		return num >= min && num <= max;
	}
}
