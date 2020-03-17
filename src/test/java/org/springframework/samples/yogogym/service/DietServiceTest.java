package org.springframework.samples.yogogym.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Diet;
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
	

}
