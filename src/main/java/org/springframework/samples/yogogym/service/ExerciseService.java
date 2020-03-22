
package org.springframework.samples.yogogym.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public Collection<Exercise> findAllExercise() throws DataAccessException {

		Collection<Exercise> res = new ArrayList<>();

		for (Exercise e : this.exerciseRepository.findAll()) {
			res.add(e);
		}

		return res;
	}

	@Transactional(readOnly = true)
	public Exercise findExerciseById(final int id) throws DataAccessException {
		return this.exerciseRepository.findById(id);
	}

}
