package org.springframework.samples.yogogym.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
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
		assertThat(trainings.size()).isEqualTo(10);
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
		assertThat(trainings.size()).isEqualTo(3);
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
	public void shouldCountConcurrentTrainingsForInit() throws ParseException {
		Collection<Training> concurrentTrainings = this.trainingService.countConcurrentTrainingsForInit(CLIENT_ID, -1, dateFormat.parse("2020-01-05"));
		assertThat(concurrentTrainings).isNotNull();
		assertThat(concurrentTrainings.size()).isEqualTo(1);
		Training training = EntityUtils.getById(concurrentTrainings, Training.class, TRAINING_ID);
		assertThat(training.getName()).isEqualTo("Entrenamiento1");
		assertThat(dateFormat.format(training.getInitialDate())).isEqualTo("2020-01-01");
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo("2020-01-14");
		assertThat(training.getClient().getUser().getUsername()).isEqualTo("client1");
	}
	
	@Test
	public void shouldCountConcurrentTrainingsForEnd() throws ParseException {
		Collection<Training> concurrentTrainings = this.trainingService.countConcurrentTrainingsForEnd(CLIENT_ID, -1, dateFormat.parse("2020-01-12"));
		assertThat(concurrentTrainings).isNotNull();
		assertThat(concurrentTrainings.size()).isEqualTo(1);
		Training training = EntityUtils.getById(concurrentTrainings, Training.class, TRAINING_ID);
		assertThat(training.getName()).isEqualTo("Entrenamiento1");
		assertThat(dateFormat.format(training.getInitialDate())).isEqualTo("2020-01-01");
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo("2020-01-14");
		assertThat(training.getClient().getUser().getUsername()).isEqualTo("client1");
	}
	
	@Test
	public void shouldCountConcurrentTrainingsForIncluding() throws ParseException {
		Collection<Training> concurrentTrainings = this.trainingService.countConcurrentTrainingsForIncluding(CLIENT_ID, -1, dateFormat.parse("2019-12-31"), dateFormat.parse("2020-01-15"));
		assertThat(concurrentTrainings).isNotNull();
		assertThat(concurrentTrainings.size()).isEqualTo(1);
		Training training = EntityUtils.getById(concurrentTrainings, Training.class, TRAINING_ID);
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
		
		Training training = createSampleTraining(2,7);
		Calendar initDate = (Calendar) now.clone();
		initDate.add(Calendar.DAY_OF_MONTH,2);
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
	
	@ParameterizedTest
	@ValueSource(ints = {-1,0})
	@Transactional
	public void shouldNotInsertTrainingDueToEndBeforeEqualsInit(int addEndDate) throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,addEndDate);
		
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
	
	@ParameterizedTest
	@ValueSource(ints = {2,4,6,7})
	@Transactional
	public void shouldNotInsertTrainingDueToInitInTraining(int addInitialDate) throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(2,7);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(addInitialDate,20);
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+2);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+2);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {7,8,13,14})
	@Transactional
	public void shouldNotInsertTrainingDueToEndInTraining(int addEndDate) throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(7,14);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(0,addEndDate);
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
		
		Training training = createSampleTraining(3,8);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(2,9);
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
		
		Training training = createSampleTraining(2,7);
		this.trainingService.saveTraining(training);
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient+1);
		
		List<Training> clientTrainingsList = new ArrayList<Training>(clientTrainings);
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
		
		clientTrainingsList = new ArrayList<Training>(clientTrainings);
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
		
		Training training = createSampleTraining(2,9);
		this.trainingService.saveTraining(training);
		
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		List<Training> clientTrainingsList = new ArrayList<Training>(clientTrainings);
		Training afterCreateTraining = clientTrainingsList.get(clientTrainingsList.size()-1);
		
		String newName = "New Training Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, 100);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(afterCreateTraining, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		
		assertThrows(LongerThan90DaysException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		clientTrainingsList = new ArrayList<Training>(clientTrainings);
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getName()).isNotEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isNotEqualTo(dateFormat.format(newEndDate));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {8,9,14,15})
	@Transactional
	public void shouldNotUpdateTrainingDueToEndInTraining(int addEndDate) throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Training training2 = createSampleTraining(8,15);
		this.trainingService.saveTraining(training2);
		Training training = createSampleTraining(2,7);
		this.trainingService.saveTraining(training);
		
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		List<Training> clientTrainingsList =  new ArrayList<Training>(clientTrainings);
		Training afterCreateTraining = clientTrainingsList.get(clientTrainingsList.size()-1);
		
		String newName = "New Training Updated";
		Calendar aux = (Calendar) now.clone();
		aux.add(Calendar.DAY_OF_MONTH, addEndDate);
		Date newEndDate = aux.getTime();
		
		Training auxTraining = new Training();
		BeanUtils.copyProperties(afterCreateTraining, auxTraining);
		auxTraining.setName(newName);
		auxTraining.setEndDate(newEndDate);
		
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(auxTraining));
		
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		clientTrainingsList = new ArrayList<Training>(clientTrainings);
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getName()).isNotEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isNotEqualTo(dateFormat.format(newEndDate));
		
	}
	
	@Test
	@Transactional
	public void shouldNotUpdateTrainingDueToIncludingTraining() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException {
		
		Training training2 = createSampleTraining(8,15);
		this.trainingService.saveTraining(training2);
		Training training = createSampleTraining(2,7);
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
		
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		clientTrainingsList = new ArrayList<Training>(clientTrainings);
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getName()).isNotEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isNotEqualTo(dateFormat.format(newEndDate));
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
	
	//Copy Training
	@Test
	@Transactional
	public void shouldFindTrainingWithPublicClient() {
		Collection<Training> trainingsPublic = this.trainingService.findTrainingWithPublicClient();
		assertThat(trainingsPublic.size()).isEqualTo(11);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
	@Transactional
	public void shouldFindTrainingIdFromClient(int idClient) {
		Collection<Training> listTraining = this.trainingService.findTrainingFromClient(idClient);
		Collection<Integer> listTrainingsId = this.trainingService.findTrainingIdFromClient(idClient);
		assertThat(listTrainingsId.size()).isEqualTo(listTraining.size());
	}
	
	/**
	 * <p>Creates a sample Training whose initial and end date are by default the actual date. Also, its 
	 * client is the one with the id CLIENT_ID.</p>
	 * @param addInitDate : Days added to the training's initial date. 
	 * @param addEndDate : Days added to the training's end date. 
	 */
	private Training createSampleTraining(int addInitDate, int addEndDate) {
		
		Calendar initDate = (Calendar) now.clone();
		Calendar endDate = (Calendar) now.clone();
		
		initDate.add(Calendar.DAY_OF_MONTH, addInitDate);
		endDate.add(Calendar.DAY_OF_MONTH, addEndDate);
		
		Training training = new Training();
		training.setName("New Training");
		training.setInitialDate(initDate.getTime());
		training.setEndDate(endDate.getTime());
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
		Client client = this.clientService.findClientById(CLIENT_ID);
		training.setClient(client);
		
		return training;
	}
}