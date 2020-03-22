package org.springframework.samples.yogogym.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.RoutineLine;

public interface RoutineLineRepository extends  CrudRepository<RoutineLine, String>{

	@Query("SELECT routineLine FROM RoutineLine routineLine WHERE routineLine.id=:id")
	RoutineLine findRoutineLineById(@Param("id")int id);

	@Query("SELECT routineLine FROM RoutineLine routineLine")
	Collection<RoutineLine> findAllRoutines();

}
