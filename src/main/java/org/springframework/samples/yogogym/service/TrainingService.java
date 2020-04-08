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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.ClientRepository;
import org.springframework.samples.yogogym.repository.TrainingRepository;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class TrainingService {

	private TrainingRepository trainingRepository;
	private ClientRepository clientRepository;

	@Autowired
	public TrainingService(TrainingRepository trainingRepository, ClientRepository clientRepository) {
		this.trainingRepository = trainingRepository;
		this.clientRepository = clientRepository;
	}
	
	@Transactional
	public Collection<Training> findAllTrainings() throws DataAccessException {
		
		Collection<Training> res = (Collection<Training>) this.trainingRepository.findAll();
		
		return res;
	}
	
	@Transactional
	public Collection<Training> findTrainingFromClient(int clientId) throws DataAccessException {
		
		Collection<Training> res = this.trainingRepository.findTrainingFromClient(clientId);
		
		return res;		
	}
	
	@Transactional
	public Training findTrainingById(int trainingId) throws DataAccessException {
		
		Training res = this.trainingRepository.findTrainingById(trainingId);
		
		return res;		
	}
	
	@SuppressWarnings("deprecation")
	@Transactional(rollbackFor = {PastInitException.class, PastEndException.class, EndBeforeEqualsInitException.class, 
		InitInTrainingException.class, EndInTrainingException.class, PeriodIncludingTrainingException.class, LongerThan90DaysException.class})
	
	public void saveTraining(Training training) throws DataAccessException, PastInitException, EndBeforeEqualsInitException,
	InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Client client = training.getClient();
		Date initialDate = training.getInitialDate();
		Date endDate = training.getEndDate();
		
		long diffInMillies = Math.abs(endDate.getTime()-initialDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		Date now = new Date();
		now = new Date(now.getYear(), now.getMonth(), now.getDate());
		
		Boolean anyException = true;

		// No training ending in the past
		if(!training.isNew()) {
			Training oldTraining = this.trainingRepository.findTrainingById(training.getId());
			if(endDate.before(now) && !endDate.equals(oldTraining.getEndDate())){
				anyException = false;
				throw new PastEndException();
			}	
		}
		// Training starting in the past?
		if(training.isNew() && initialDate.before(now)) {
			anyException = false;
			throw new PastInitException();
		}
		// End date before or equals to initial date?
		else if(endDate.compareTo(initialDate)<=0) {
			anyException = false;
			throw new EndBeforeEqualsInitException();
		}
		// Training longer than 90 days?
		else if(diff>90) {
			anyException = false;
			throw new LongerThan90DaysException();
		}
		
		if (anyException) {
			// Concurrent trainings?
			int trainingId = -1;
			if(!training.isNew()) {
				trainingId = training.getId();
			}
			
			Collection<Training> concurrentInit = this.trainingRepository.countConcurrentTrainingsForInit(training.getClient().getId(), 
				trainingId, training.getInitialDate());
			Collection<Training> concurrentEnd = this.trainingRepository.countConcurrentTrainingsForEnd(training.getClient().getId(), 
				trainingId, training.getEndDate());
			Collection<Training> concurrentIncluding = this.trainingRepository.countConcurrentTrainingsForIncluding(training.getClient().getId(), 
				trainingId, training.getInitialDate(), training.getEndDate());
			
			if(concurrentInit.size()>0) {
				anyException=false;
				List<Training> aux = new ArrayList<Training>(concurrentInit);
				Training t = aux.get(0);
				throw new InitInTrainingException(dateFormat.format(t.getInitialDate()),dateFormat.format(t.getEndDate()));
			}
			else if(concurrentEnd.size()>0){
				anyException=false;
				List<Training> aux = new ArrayList<Training>(concurrentEnd);
				Training t = aux.get(0);
				throw new EndInTrainingException(dateFormat.format(t.getInitialDate()),dateFormat.format(t.getEndDate()));
			}
			else if(concurrentIncluding.size()>0) {
				anyException=false;
				List<Training> aux = new ArrayList<Training>(concurrentIncluding);
				Training t = aux.get(0);
				throw new PeriodIncludingTrainingException(dateFormat.format(t.getInitialDate()),dateFormat.format(t.getEndDate()));
			}
		}
		if(anyException) {
			// Checking if it is create or update
			if(training.isNew()) {
				client.getTrainings().add(training);
				this.clientRepository.save(client);
			}
			else {
				this.trainingRepository.save(training);
			}
		}
	}

	
	@Transactional
	public void deleteTraining(Training training) throws DataAccessException {
		Client client = training.getClient();
		client.getTrainings().remove(training);
		this.clientRepository.save(client);
		this.trainingRepository.delete(training);
	}
	
	//Copy training
	@Transactional
	public Collection<Training> findTrainingWithPublicClient() throws DataAccessException{
		Collection<Training> res = this.trainingRepository.findTrainingWithPublicClient();
		return res;
	}
	
	@Transactional
	public Collection<Integer> findTrainingIdFromClient(int id) throws DataAccessException{
		return this.trainingRepository.findTrainingIdFromClient(id);
	}
}
