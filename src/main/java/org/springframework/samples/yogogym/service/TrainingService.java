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
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.ClientRepository;
import org.springframework.samples.yogogym.repository.TrainingRepository;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
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
		// No training ending in the past
		if(!training.isNew()) {
			Training oldTraining = this.trainingRepository.findTrainingById(training.getId());
			if(endDate.before(now) && !endDate.equals(oldTraining.getEndDate())){
				anyException = false;
				throw new PastEndException();
			}	
		}
		if (anyException) {
			// Period without other trainings
			List<Training> associatedTrainings = training.getClient().getTrainings()
				.stream().sorted(Comparator.comparing(Training::getInitialDate)).collect(Collectors.toList());
			for(Training t : associatedTrainings) {
				if(training.isNew()||!t.getId().equals(training.getId())) {
					
					Date initDateAssoc = t.getInitialDate();
					String initAssoc = dateFormat.format(initDateAssoc);
					Date endDateAssoc = t.getEndDate();
					String endAssoc = dateFormat.format(endDateAssoc);

					Boolean initInPeriod = initialDate.compareTo(initDateAssoc)>=0&&initialDate.compareTo(endDateAssoc)<=0;
					Boolean endInPeriod = endDate.compareTo(initDateAssoc)>=0&&endDate.compareTo(endDateAssoc)<=0;
					Boolean initAssocInPeriod = initDateAssoc.compareTo(initialDate)>=0&&initDateAssoc.compareTo(endDate)<=0;
					Boolean endAssocInPeriod = endDateAssoc.compareTo(initialDate)>=0&&endDateAssoc.compareTo(endDate)<=0;
					
					if(initInPeriod) {
						anyException=false;
						throw new InitInTrainingException(initAssoc,endAssoc);
					}
					else if(endInPeriod) {
						anyException=false;
						throw new EndInTrainingException(initAssoc,endAssoc);
					}
					else if(initAssocInPeriod && endAssocInPeriod) {
						anyException=false;
						throw new PeriodIncludingTrainingException(initAssoc,endAssoc);
					}
					if(!anyException) {
						break;
					}
				}
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
}
