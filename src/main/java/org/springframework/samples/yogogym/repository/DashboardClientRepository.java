package org.springframework.samples.yogogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.DashboardClient;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Training;

public interface DashboardClientRepository extends CrudRepository<DashboardClient, Integer>{
		
	@Query("SELECT t FROM Client c left join c.trainings t WHERE c.id = :id")
	List<Training> findIdTrainingByClient(@Param("id") int id);
	
	@Query("SELECT r.id FROM Training t left join t.routines r WHERE t.id = :id")
	List<Integer> findIdRoutineByIdTraining(@Param("id") int id);
	
	@Query("SELECT r.repsPerWeek FROM Routine r WHERE r.id = :id")
	Integer findRepsPerWeekByIdRoutine(@Param("id") int id);
	
	@Query("SELECT rl.exercise FROM Routine r left join r.routineLine rl WHERE r.id = :id")
	List<Exercise> findIdExerciseByIdRoutine(@Param("id") int id);

}
