package org.springframework.samples.yogogym.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.DashboardAdmin;
import org.springframework.samples.yogogym.model.Training;

public interface DashboardsAdminRepository extends CrudRepository<DashboardAdmin, Integer>{
	
	/* Equipment control */
	
	//
	
	@Query("SELECT t FROM Training t")
	Collection<Training> findIdTrainingByDate();
	
	@Query("SELECT r.id FROM Training t left join t.routines r WHERE t.id = :id")
	List<Integer> findIdRoutineByIdTraining(@Param("id") int id);
	
	@Query("SELECT r.repsPerWeek FROM Routine r WHERE r.id = :id")
	Integer findRepsPerWeekByIdRoutine(@Param("id") int id);
	
	@Query("SELECT rl.exercise.id FROM Routine r left join r.routineLine rl WHERE r.id = :id")
	List<Integer> findIdExerciseByIdRoutine(@Param("id") int id);
	
	@Query("SELECT e.equipment.id FROM Exercise e WHERE e.id = :id")
	Integer findIdEquipmentByIdExercise(@Param("id") int id);
	
	@Query("SELECT eq.name FROM Equipment eq WHERE eq.id = :id")
	String findNameEquipmentByIdEquipment(@Param("id") int id);

}
