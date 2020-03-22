package org.springframework.samples.yogogym.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
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
public class TrainingServiceTest {
	
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private ClientService clientService;
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final int CLIENT_ID=1;
	private final int TRAINING_ID=1;
	private final Date now = new Date();
	
	//TODO CHECK ALL ATTRIBUTES
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
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void shouldInsertTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
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
		assertThat(training.getId().longValue()).isNotNull();
		assertThat(training.getName()).isEqualTo("Nuevo Entrenamiento");
		assertThat(dateFormat.format(training.getInitialDate()))
		.isEqualTo(dateFormat.format(new Date(now.getYear(), now.getMonth(), now.getDate())));
		assertThat(dateFormat.format(training.getEndDate()))
		.isEqualTo(dateFormat.format(new Date(now.getYear(), now.getMonth(), now.getDate()+7)));
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
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToEndBeforeEqualsInit() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,-1);
		
		assertThrows(EndBeforeEqualsInitException.class, ()->this.trainingService.saveTraining(training));
		
		training.setEndDate(new Date(now.getYear(), now.getMonth(), now.getDate()));
		assertThrows(EndBeforeEqualsInitException.class, ()->this.trainingService.saveTraining(training));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+1);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToInitInTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(0,7);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(0,14);
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		training2.setInitialDate(new Date(now.getYear(), now.getMonth(), now.getDate()+1));
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		training2.setInitialDate(new Date(now.getYear(), now.getMonth(), now.getDate()+6));
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		training2.setInitialDate(new Date(now.getYear(), now.getMonth(), now.getDate()+7));
		assertThrows(InitInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+2);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+2);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void shouldNotInsertTrainingDueToEndInTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
		Collection<Training> allTrainings = this.trainingService.findAllTrainings();
		int foundAll = allTrainings.size();
		Collection<Training> clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int foundClient = clientTrainings.size();
		
		Training training = createSampleTraining(7,14);
		this.trainingService.saveTraining(training);
		
		Training training2 = createSampleTraining(0,7);
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		training2.setEndDate(new Date(now.getYear(), now.getMonth(), now.getDate()+8));
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		training2.setEndDate(new Date(now.getYear(), now.getMonth(), now.getDate()+13));
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		training2.setEndDate(new Date(now.getYear(), now.getMonth(), now.getDate()+14));
		assertThrows(EndInTrainingException.class, ()->this.trainingService.saveTraining(training2));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isNotEqualTo(foundAll+2);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isNotEqualTo(foundClient+2);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertTrainingDuePeriodIncludingTraining() throws DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException{
		
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
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void shouldUpdateTraining() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException {
		
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
		
		String newName = "Nuevo Entrenamiento Actualizado";
		Date newEndDate = new Date(now.getYear(), now.getMonth(), now.getDate()+5);
		training.setName(newName);
		training.setEndDate(newEndDate);
		
		this.trainingService.saveTraining(training);
		
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getName()).isEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo(dateFormat.format(new Date(now.getYear(), now.getMonth(), now.getDate()+5)));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient+1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void shouldNotUpdateTrainingDueToPastEnd() throws  DataAccessException, PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException {
		
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
		
		String newName = "Nuevo Entrenamiento Actualizado";
		Date newEndDate = new Date(now.getYear(), now.getMonth(), now.getDate()+5);
		training.setName(newName);
		training.setEndDate(newEndDate);
		
		this.trainingService.saveTraining(training);
		
		training = clientTrainingsList.get(clientTrainingsList.size()-1);
		assertThat(training.getName()).isEqualTo(newName);
		assertThat(dateFormat.format(training.getEndDate())).isEqualTo(dateFormat.format(new Date(now.getYear(), now.getMonth(), now.getDate()+5)));
		
		allTrainings = this.trainingService.findAllTrainings();
		assertThat(allTrainings.size()).isEqualTo(foundAll+1);
		clientTrainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(clientTrainings.size()).isEqualTo(foundClient+1);
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
	
	@SuppressWarnings("deprecation")
	private Training createSampleTraining(int addInitDate, int addEndDate) {
		
		Training training = new Training();
		training.setName("Nuevo Entrenamiento");
		training.setInitialDate(new Date(now.getYear(), now.getMonth(), now.getDate() + addInitDate));
		training.setEndDate(new Date(now.getYear(), now.getMonth(), now.getDate() + addEndDate));
		Client client = this.clientService.findClientById(CLIENT_ID);
		training.setClient(client);
		
		return training;
	}
}