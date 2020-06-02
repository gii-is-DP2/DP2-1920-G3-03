
package org.springframework.samples.yogogym.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class ExerciseService {

	private ExerciseRepository exerciseRepository;


	@Autowired
	public ExerciseService(final ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}
	
	@Transactional
	public void deleteExercise(final Exercise exercise) throws DataAccessException {
		this.exerciseRepository.delete(exercise);
	}

	@Transactional
	public void saveExercise(final Exercise exercise) throws DataAccessException {
		this.exerciseRepository.save(exercise);
	}

	@Transactional(readOnly=true)
	public Collection<Exercise> findAllExercise() throws DataAccessException {
		return Lists.newArrayList(this.exerciseRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Exercise findExerciseById(final int id) throws DataAccessException {
		return this.exerciseRepository.findById(id);
	}

}
