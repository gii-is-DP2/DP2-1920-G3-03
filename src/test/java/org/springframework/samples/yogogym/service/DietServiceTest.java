package org.springframework.samples.yogogym.service;



import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Intensity;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DietServiceTest {

	@Autowired
	protected DietService dietService;
	
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
	
	@Test
	void shouldCreateDiet() {
		
		Collection<Diet> diets = (Collection<Diet>) this.dietService.findAllDiet();
		int found = diets.size();
		Diet c = new Diet();
		
		c.setName("DietTest");
		c.setDescription("Test");
		c.setKcal(1);
		c.setCarb(1);
		c.setProtein(1);
		c.setFat(1);

		this.dietService.saveDiet(c);
		c = this.dietService.findDietById(found + 1);
		assertThat(c.getName()).isEqualTo("DietTest");
	}
	
	// @Test
	// void shouldUpdateOwner() {
		
	// 	Diet diet = this.dietService.findDietById(1);
	// 	diet.setName("UpdateTest");
	// 	this.dietService.saveDiet(diet);
		
	// 	diet = this.dietService.findDietById(1);
	// 	assertThat(diet.getName()).isEqualTo("UpdateTest");
	// }
	
	// @Test
	// void shouldDeleteOwner() {
		
	// 	Collection<Diet> diets = (Collection<Diet>) this.dietService.findAll();
	// 	int foundBefore = diets.size();
		
	// 	Diet diet = this.dietService.findDietById(2);
	// 	diet.setExercise(null);
	// 	this.dietService.deleteDiet(diet);
		
	// 	diets = (Collection<Diet>) this.dietService.findAll();
	// 	int foundAfter = diets.size();
		
	// 	assertThat(foundBefore).isGreaterThan(foundAfter);
	// }
}
