package org.springframework.samples.yogogym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer>{

	
}
