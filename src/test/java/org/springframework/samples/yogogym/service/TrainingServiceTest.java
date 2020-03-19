package org.springframework.samples.yogogym.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeInitException;
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
	
	@Test
	public void shouldFindAllClientTrainings() {
		Collection<Training> trainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(trainings.size()).isEqualTo(2);
		Training training = EntityUtils.getById(trainings, Training.class, TRAINING_ID);
		assertThat(training.getName()).isEqualTo("Entrenamiento1");
		assertThat(dateFormat.format(training.getInitialDate()).toString()).isEqualTo("2020-01-01");
		assertThat(dateFormat.format(training.getEndDate()).toString()).isEqualTo("2020-01-14");
	}
	
	@Test
	@Transactional
	public void shouldInsertTraining() throws ParseException, DataAccessException, PastInitException, EndBeforeInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException {
		Collection<Training> trainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int found = trainings.size();
		
		Training training = new Training();
		training.setName("Nuevo Entrenamiento");
		training.setInitialDate(dateFormat.parse("2020-05-01"));
		training.setEndDate(dateFormat.parse("2020-05-21"));
		Client client = this.clientService.findClientById(CLIENT_ID);
		training.setClient(client);
		
		this.trainingService.saveTraining(training);
		assertThat(training.getId().longValue()).isNotEqualTo(0);
		
		trainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(trainings.size()).isEqualTo(found+1);
	}
	
	@Test
	@Transactional
	public void shouldUpdateTraining() throws ParseException, DataAccessException, PastInitException, EndBeforeInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException {
		Training training = this.trainingService.findTrainingById(TRAINING_ID);
		String newName = "Entrenamiento Actualizado";
		Date newInitDate = dateFormat.parse("2020-06-01");
		Date newEndDate = dateFormat.parse("2020-06-21");
		
		training.setName(newName);
		training.setInitialDate(newInitDate);
		training.setEndDate(newEndDate);
		
		this.trainingService.saveTraining(training);
		
		training = this.trainingService.findTrainingById(TRAINING_ID);
		assertThat(training.getName()).isEqualTo(newName);
		assertThat(dateFormat.format(training.getInitialDate()).toString()).isEqualTo("2020-06-01");
		assertThat(dateFormat.format(training.getEndDate()).toString()).isEqualTo("2020-06-21");
	}
	
	@Test
	@Transactional
	public void shouldDeleteTraining() {
		Collection<Training> trainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		int found = trainings.size();
		
		Training training = this.trainingService.findTrainingById(TRAINING_ID);
		assertThat(training).isNotNull();
		this.trainingService.deleteTraining(training);
		assertThat(this.trainingService.findTrainingById(TRAINING_ID)).isNull();
		
		trainings = this.trainingService.findTrainingFromClient(CLIENT_ID);
		assertThat(trainings.size()).isEqualTo(found-1);
	}
}