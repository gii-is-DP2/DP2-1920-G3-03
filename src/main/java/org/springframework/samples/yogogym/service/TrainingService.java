package org.springframework.samples.yogogym.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingService {

	private TrainingRepository trainingRepository;
	private ClientRepository clientRepository;

	@Autowired
	public TrainingService(TrainingRepository trainingRepository, ClientRepository clientRepository) {
		this.trainingRepository = trainingRepository;
		this.clientRepository = clientRepository;
	}
	
	@Transactional(readOnly=true)
	public Collection<Training> findAllTrainings() throws DataAccessException {
		
		return (Collection<Training>) this.trainingRepository.findAll();
	}
	
	@Transactional
	public Collection<Training> findTrainingFromClient(int clientId) throws DataAccessException {
		
		return this.trainingRepository.findTrainingFromClient(clientId);
	}
	
	@Transactional
	public Training findTrainingById(int trainingId) throws DataAccessException {
		
		return this.trainingRepository.findTrainingById(trainingId);	
	}
	
	@Transactional(rollbackFor = {PastInitException.class, PastEndException.class, EndBeforeEqualsInitException.class, 
		InitInTrainingException.class, EndInTrainingException.class, PeriodIncludingTrainingException.class, LongerThan90DaysException.class})
	
	public void saveTraining(Training training, Client client) throws DataAccessException, PastInitException, EndBeforeEqualsInitException,
	InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date initialDate = training.getInitialDate();
		Date endDate = training.getEndDate();
		
		long diffInMillies = Math.abs(endDate.getTime()-initialDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		Calendar nowCal = Calendar.getInstance();
		nowCal.set(Calendar.HOUR_OF_DAY, 0);
		nowCal.set(Calendar.MINUTE, 0);
		nowCal.set(Calendar.SECOND, 0);
		nowCal.set(Calendar.MILLISECOND,0);
		
		Date now = nowCal.getTime();
		
		Boolean anyException = true;

		// No training ending in the past
		if(!training.isNew()) {
			Training oldTraining = this.trainingRepository.findTrainingById(training.getId());
			
			String endDateFormat = dateFormat.format(endDate);
			String oldEndDateFormat = dateFormat.format(oldTraining.getEndDate());
			
			if(endDate.before(now) && !endDateFormat.equals(oldEndDateFormat)){
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
			
			List<Integer> ids = client.getTrainings().stream().map(x->Integer.valueOf(x.getId())).collect(Collectors.toList());
			Collection<Training> concurrentInit = this.countConcurrentTrainingsForInit(ids, 
				trainingId, training.getInitialDate());
			Collection<Training> concurrentEnd = this.countConcurrentTrainingsForEnd(ids, 
				trainingId, training.getEndDate());
			Collection<Training> concurrentIncluding = this.countConcurrentTrainingsForIncluding(ids, 
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
	
	@Transactional(readOnly=true)
	public Collection<Training> countConcurrentTrainingsForInit(List<Integer> ids, int trainingId, Date init) {
		return this.trainingRepository.countConcurrentTrainingsForInit(ids, trainingId, init);
	}
	
	@Transactional(readOnly=true)
	public Collection<Training> countConcurrentTrainingsForEnd(List<Integer> ids, int trainingId, Date end) {
		return this.trainingRepository.countConcurrentTrainingsForEnd(ids, trainingId, end);
	}
	
	@Transactional(readOnly=true)
	public Collection<Training> countConcurrentTrainingsForIncluding(List<Integer> ids, int trainingId, Date init, Date end) {
		return this.trainingRepository.countConcurrentTrainingsForIncluding(ids, trainingId, init, end);
	}

	
	@Transactional
	public void deleteTraining(Training training, Client client) throws DataAccessException {
		client.getTrainings().remove(training);
		this.clientRepository.save(client);
		this.trainingRepository.delete(training);
	}
	
	//Copy training
	@Transactional(readOnly=true)
	public Collection<Training> findTrainingWithPublicClient() throws DataAccessException{
		List<Training> res = (List<Training>) this.trainingRepository.findTrainingWithPublicClient();
		return res.get(0) == null ? new ArrayList<Training>() : res;
	}
	
	@Transactional(readOnly=true)
	public Collection<Integer> findTrainingIdFromClient(int id) throws DataAccessException{
		List<Integer> res = (List<Integer>) this.trainingRepository.findTrainingIdFromClient(id);
		return res.get(0) == null ? new ArrayList<Integer>() : res;
	}
}
