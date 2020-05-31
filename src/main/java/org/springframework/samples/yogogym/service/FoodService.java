package org.springframework.samples.yogogym.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Food;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.repository.DietRepository;
import org.springframework.samples.yogogym.repository.FoodRepository;
import org.springframework.samples.yogogym.service.exceptions.FoodDuplicatedException;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodService {

	private FoodRepository foodRepository;

	
	@Autowired
	public FoodService(FoodRepository foodRepository, DietRepository dietRepository, DietService dietService) {
		this.foodRepository = foodRepository;
	
	}
	
	@Transactional(readOnly=true)
	public Collection<Food> findAllFoods() throws DataAccessException {
		return this.foodRepository.findAllFoods();
	}
	
	@Transactional(readOnly=true)
	public Food findFoodById(Integer foodId) throws DataAccessException {	
		return this.foodRepository.findFoodById(foodId);	
	}
	
	
	
}
