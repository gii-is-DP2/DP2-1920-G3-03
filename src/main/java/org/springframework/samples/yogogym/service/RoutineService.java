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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.RoutineRepository;
import org.springframework.samples.yogogym.service.exceptions.TrainingNotFinished;
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

	@Autowired
	public RoutineService(RoutineRepository routineRepository, TrainingService trainingService) {
		this.routineRepository = routineRepository;
		this.trainingService = trainingService;
	}
	
	
	@Transactional(rollbackFor= {TrainingNotFinished.class})
	public void saveRoutine(Routine routine, int trainingId) throws DataAccessException, TrainingNotFinished {
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingNotFinished();
		else
			routineRepository.save(routine);
	}
	
	@Transactional(rollbackFor= {TrainingNotFinished.class})
	public void deleteRoutine(Routine routine, int trainingId) throws DataAccessException, TrainingNotFinished {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		if(training.getEndDate().before(actualDate))
			throw new TrainingNotFinished();
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
}
