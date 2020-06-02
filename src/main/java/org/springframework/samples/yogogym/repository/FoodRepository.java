package org.springframework.samples.yogogym.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Food;

public interface FoodRepository extends CrudRepository<Food, String>{
	
	@Query("select f from Food f where f.id=:id")
	public Food findFoodById(@Param("id")int id);
	
	@Query("select f from Food f")
	Collection<Food> findAllFoods();

}
