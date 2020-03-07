
package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, String> {

	@Query("SELECT exercise FROM Exercise exercise WHERE exercise.id=:id")
	Exercise findById(@Param("id") int id);
}
