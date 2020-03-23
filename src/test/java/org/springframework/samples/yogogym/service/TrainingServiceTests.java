package org.springframework.samples.yogogym.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
import org.springframework.samples.yogogym.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TrainingServiceTests {
	
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private ClientService clientService;
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final int CLIENT_ID=1;
	private final int TRAINING_ID=1;
	private final static Calendar now = Calendar.getInstance();
	
	@BeforeAll
	public static void setup() {
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
	}
	
	@Test
	public void shouldFindAllTrainings() {
		Collection<Training> trainings = this.trainingService.findAllTrainings();
		assertThat(trainings).isNotNull();
		assertThat(trainings.size()).isEqualTo(8);
		Training training = EntityUtils.getById(trainings, Training.class, TRAINING_ID);
		assertThat(training.getName()).isEqualTo("Entrenamiento1");
		assertThat(dateFormat.format(training.getInitialDate())).isEqualTo("2020-01-01");
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo("2020-01-14");
		assertThat(training.getClient().getUser().getUsername()).isEqualTo("client1");
	}
	
	@Test
	public void shouldFindAllClientTrainings() {
		Collection<Training> trainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(trainings).isNotNull();
		assertThat(trainings.size()).isEqualTo(2);
		Training training = EntityUtils.getById(trainings, Training.class, TRAINING_ID);
		assertThat(training.getName()).isEqualTo("Entrenamiento1");
		assertThat(dateFormat.format(training.getInitialDate())).isEqualTo("2020-01-01");
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo("2020-01-14");
		assertThat(training.getClient().getUser().getUsername()).isEqualTo("client1");
	}
	
	@Test
	public void shouldFindCorrectTraining() {
		Training training = this.trainingService.findTrainingById(TRAINING_ID);
		assertThat(training.getName()).isEqualTo("Entrenamiento1");
		assertThat(dateFormat.format(training.getInitialDate())).isEqualTo("2020-01-01");
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo("2020-01-14");
		assertThat(training.getClient().getUser().getUsername()).isEqualTo("client1");
	}
	
	@Test
	@Transactional
	public void shouldInsertTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,7);
		Calendar initDate = (Calendar) now.clone();
		Calendar endDate = (Calendar) now.clone();
		endDate.add(Calendar.DAY_OF_MONTH, 7);
		
		this.trainingService.saveTraining(training);
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient+1);
		
		List<Training> clientTrainingsList = (List<Training>) clientTrainings;
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getId().longValue()).isNotNull();
		assertThat(training.getName()).isEqualTo("New Training");
		assertThat(dateFormat.format(training.getInitialDate()))
		.isEqualTo(dateFormat.format(initDate.getTime()));
		assertThat(dateFormat.format(training.getEndDate()))
		.isEqualTo(dateFormat.format(endDate.getTime()));
		assertThat(training.getClient().getUser().getUsername()).isEqualTo("client1");
		
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToPastInitDate() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(-1,7);
			
		assertThrows(PastInitException.class, ()->this.trainingService.saveTraining(training));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+1);
		
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToEndBeforeEqualsInit() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,-1);
		
		assertThrows(EndBeforeEqualsInitException.class, ()->this.trainingService.saveTraining(training));
		
		Calendar endDate = (Calendar) now.clone();
		training.setEndDate(endDate.getTime());
		assertThrows(EndBeforeEqualsInitException.class, ()->this.trainingService.saveTraining(training));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+1);
		
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToLongerThan90Days() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,92);
		
		assertThrows(LongerThan90DaysException.class, ()->this.trainingService.saveTraining(training));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+1);
		
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToInitInTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,7);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(0,14);
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		Calendar initDate = (Calendar) now.clone();
		initDate.add(Calendar.DAY_OF_MONTH, 1);
		training2.setInitialDate(initDate.getTime());
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		initDate = (Calendar) now.clone();
		initDate.add(Calendar.DAY_OF_MONTH, 6);
		training2.setInitialDate(initDate.getTime());
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		initDate = (Calendar) now.clone();
		initDate.add(Calendar.DAY_OF_MONTH, 7);
		training2.setInitialDate(initDate.getTime());
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+2);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+2);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToEndInTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(7,14);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(0,7);
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		Calendar endDate = (Calendar) now.clone();
		endDate.add(Calendar.DAY_OF_MONTH, 8);
		training2.setEndDate(endDate.getTime());
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		endDate = (Calendar) now.clone();
		endDate.add(Calendar.DAY_OF_MONTH, 13);
		training2.setEndDate(endDate.getTime());
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		endDate = (Calendar) now.clone();
		endDate.add(Calendar.DAY_OF_MONTH, 14);
		training2.setEndDate(endDate.getTime());
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+2);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+2);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDuePeriodIncludingTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(1,8);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(0,9);
		assertThrows(PeriodIncludingTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+2);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+2);
	}
	
	@Test
	@Transactional
	public void shouldUpdateTraining() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,7);
		this.trainingService.saveTraining(training);
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient+1);
		
		List<Training> clientTrainingsList = (List<Training>) clientTrainings;
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		
		String newName = "New Training Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 5);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(training, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		this.trainingService.saveTraining(auxTraining);
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient+1);
		
		clientTrainingsList = (List<Training>) clientTrainings;
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getName()).isEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo(dateFormat.format(newEndDate));
	}
	
	@Test
	@Transactional
	public void shouldNotUpdateTrainingDueToPastEnd() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Training training = this.trainingService.findTrainingById(TRAINING_ID);
		
		String newName = "Entrenamiento 1 Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, -1);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(training, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		
		assertThrows(PastEndException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		training = this.trainingService.findTrainingById(TRAINING_ID);
		assertThat(training.getName()).isNotEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isNotEqualTo(dateFormat.format(newEndDate));
	}
	
	@Test
	@Transactional
	public void shouldNotUpdateTrainingDueToLongerThan90Days() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Training training = createSampleTraining(0,7);
		this.trainingService.saveTraining(training);
		
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		List<Training> clientTrainingsList = (List<Training>) clientTrainings;
		Training afterCreateTraining = clientTrainingsList.get(clientTrainingsList.size()-1);
		
		String newName = "New Training Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 92);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(afterCreateTraining, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		
		assertThrows(LongerThan90DaysException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		training = this.trainingService.findTrainingById(TRAINING_ID);
		assertThat(training.getName()).isNotEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isNotEqualTo(dateFormat.format(newEndDate));
	}
	
	@Test
	@Transactional
	public void shouldNotUpdateTrainingDueToEndInTraining() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Training training2 = createSampleTraining(8,15);
		this.trainingService.saveTraining(training2);
		Training training = createSampleTraining(0,7);
		this.trainingService.saveTraining(training);
		
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		List<Training> clientTrainingsList = (List<Training>) clientTrainings;
		Training afterCreateTraining = clientTrainingsList.get(clientTrainingsList.size()-1);
		
		String newName = "New Training Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 8);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(afterCreateTraining, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 9);
		newEndDate = aux.getTime();
		auxTraining.setEndDate(newEndDate);
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 14);
		newEndDate = aux.getTime();
		auxTraining.setEndDate(newEndDate);
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 15);
		newEndDate = aux.getTime();
		auxTraining.setEndDate(newEndDate);
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(auxTraining));
		
	}
	
	@Test
	@Transactional
	public void shouldNotUpdateTrainingDueToIncludingTraining() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Training training2 = createSampleTraining(8,15);
		this.trainingService.saveTraining(training2);
		Training training = createSampleTraining(0,7);
		this.trainingService.saveTraining(training);
		
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		List<Training> clientTrainingsList = (List<Training>) clientTrainings;
		Training afterCreateTraining = clientTrainingsList.get(clientTrainingsList.size()-1);
		
		String newName = "New Training Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 16);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(afterCreateTraining, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		
		assertThrows(PeriodIncludingTrainingException.class, ()->this.trainingService.saveTraining(auxTraining));
		
	}
	
	@Test
	@Transactional
	public void shouldDeleteTraining() {
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = this.trainingService.findTrainingById(TRAINING_ID);
		assertThat(training).isNotNull();
		this.trainingService.deleteTraining(training);
		assertThat(this.trainingService.findTrainingById(TRAINING_ID)).isNull();
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll-1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient-1);
	}
	
	private Training createSampleTraining(int addInitDate, int addEndDate) {
		
		Calendar initDate = (Calendar) now.clone();
		Calendar endDate = (Calendar) now.clone();
		
		initDate.add(Calendar.DAY_OF_MONTH, addInitDate);
		endDate.add(Calendar.DAY_OF_MONTH, addEndDate);
		
		Training training = new Training();
		training.setName("New Training");
		training.setInitialDate(initDate.getTime());
		training.setEndDate(endDate.getTime());
		Client client = this.clientService.findClientById(CLIENT_ID);
		training.setClient(client);
		
		return training;
	}
}