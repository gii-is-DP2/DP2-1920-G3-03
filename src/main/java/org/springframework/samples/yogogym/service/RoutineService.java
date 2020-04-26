/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
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
		else if(validRepsPerWeekValue(routine,1,20))
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
	
	@Transactional
	public Collection<Routine> findAllRoutines() throws DataAccessException {
		return (Collection<Routine>) routineRepository.findAll();
	}
	
	@Transactional
	public Collection<Routine> findAllRoutinesFromTraining(int id) throws DataAccessException {
		return routineRepository.findAllRoutinesFromTraining(id);
	}
	
	@Transactional
	public Routine findRoutineById(int routineId) throws DataAccessException {
		
		Routine res = this.routineRepository.findRoutineById(routineId);
		
		return res;		
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
		
		return num >= min && max <= 20;
	}
}
