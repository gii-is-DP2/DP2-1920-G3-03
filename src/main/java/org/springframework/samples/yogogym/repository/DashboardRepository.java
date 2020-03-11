package org.springframework.samples.yogogym.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DashboardRepository {
	
	/* Equipment control */
	
	@Query("SELECT t.id FROM TRAININGS t WHERE t.initial_date<= :initialDate and t.end_date> :endDate")
	List<Integer> findIdTrainingByDate(@Param("initialDate") Date initialDate, @Param("endDate") Date endDate);
	
	@Query("SELECT tr.routines_id FROM TRAININGS_ROUTINES tr WHERE tr.training_id = :id")
	List<Integer> findIdRoutineByIdTraining(@Param("id") int id);
	
	@Query("SELECT r.reps_per_week FROM ROUTINES r WHERE r.id = :id")
	Integer findRepsPerWeekByIdRoutine(@Param("id") int id);
	
	@Query("SELECT rl.exercise_id FROM ROUTINES_LINES rl WHERE rl.routine_id = :id")
	Integer findIdExerciseByIdRoutine(@Param("id") int id);
	
	@Query("SELECT e.equipment_id FROM EXERCISES e WHERE e.id = :id")
	Integer findIdEquipmentByIdExercise(@Param("id") int id);
	
	@Query("SELECT eq.name FROM EQUIPMENT eq WHERE eq.id = :id")
	String findNameEquipmentByIdEquipment(@Param("id") int id);

}
