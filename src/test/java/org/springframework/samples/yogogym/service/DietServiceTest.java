package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.DietType;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DietServiceTest {

	@Autowired
	protected DietService dietService;
	
	@Autowired 
	protected TrainingService trainingService;
	
	private final int trainingId = 1;
	
	@Test
	void shouldFindAllDiets(){
		Collection<Diet> diets = (Collection<Diet>) this.dietService.findAllDiet();
		assertThat(diets.size()).isEqualTo(4);
	}
	
	@Test
	void shouldFindDietById(){
		Diet diet = this.dietService.findDietById(2);
		assertThat(diet.getName()).isEqualTo("Dieta 2");	
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldCreateDiet() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = cal.getTime();
		
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newDate);
		
		try
		{
			this.trainingService.saveTraining(training);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		
		Collection<Diet> diets = (Collection<Diet>) this.dietService.findAllDiet();
		int found = diets.size();
		Diet c = new Diet();
		
		c.setName("DietTest");
		c.setDescription("Test");
		c.setKcal(1);
		c.setCarb(1);
		c.setProtein(1);
		c.setFat(1);
		c.setType(DietType.DEFINITION);
		
		try
		{
			this.dietService.saveDiet(c,training.getId());			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		c = this.dietService.findDietById(found + 1);
		assertThat(c.getName()).isEqualTo("DietTest");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1,-2,-3,-4})
	@Transactional
	public void shouldNotCreateDietBecauseLessThanZero(int kcal) throws Exception {

		Diet c = new Diet();
		
		c.setName("DietTest");
		c.setDescription("Test");
		c.setKcal(kcal);
		c.setCarb(1);
		c.setProtein(1);
		c.setFat(1);
		c.setType(DietType.DEFINITION);
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		Calendar cal = Calendar.getInstance();
		training.setInitialDate(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = cal.getTime();
		training.setEndDate(newDate);
		
		try {
			this.trainingService.saveTraining(training);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		assertThrows(Exception.class, ()->this.dietService.saveDiet(c,training.getId()));
		
	}

}
