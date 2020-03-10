package org.springframework.samples.yogogym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExerciseService {
	
	@Autowired
	private ExerciseRepository exerciseRepo;
	
	@Transactional
	public Iterable<Exercise> findAll(){
		
		return exerciseRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public Optional<Exercise> findById(int exerciseId) {
		
		return exerciseRepo.findById(exerciseId);
	}

}
