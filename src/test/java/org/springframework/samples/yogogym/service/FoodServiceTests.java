package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Food;
import org.springframework.samples.yogogym.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FoodServiceTests {
	
	@Autowired
	private FoodService foodService;

	private static final int FOOD_ID=1;

	private static final Calendar now = Calendar.getInstance();
	
	@BeforeAll
	public static void setup() {
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
	}
	
	@Test
	public void shouldFindAllFoods() {
		Collection<Food> foods = this.foodService.findAllFoods();
		assertThat(foods).isNotNull();
		assertThat(foods.size()).isEqualTo(5);
		Food food = EntityUtils.getById(foods, Food.class, FOOD_ID);
		assertThat(food.getName()).isEqualTo("Bread");
	}
	
	@Test
	public void shouldFindCorrectFood() {
		Food food = this.foodService.findFoodById(FOOD_ID);
		assertThat(food.getName()).isEqualTo("Bread");
		assertThat(food.getKcal()).isEqualTo(80);
		assertThat(food.getCarb()).isEqualTo(30);
		assertThat(food.getProtein()).isEqualTo(20);
		assertThat(food.getFat()).isEqualTo(20);
		assertThat(food.getWeight()).isEqualTo(20);
	}
	

	
}