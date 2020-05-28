package org.springframework.samples.yogogym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainerService {

	private TrainerRepository trainerRepository;

	@Autowired
	public TrainerService(TrainerRepository trainerRepository) {
		this.trainerRepository = trainerRepository;
	}
	
	@Transactional
	public Trainer findTrainer(String trainerUsername) throws DataAccessException {
		return this.trainerRepository.findTrainer(trainerUsername);	
	}
	
}
