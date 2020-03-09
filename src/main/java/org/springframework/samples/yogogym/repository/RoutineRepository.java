package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Routine;

public interface RoutineRepository extends  CrudRepository<Routine, String>{
	
	@Query("SELECT routine FROM Routine routine WHERE routine.id=:id")
	public Routine findRoutineById(@Param("id") int id);
}
