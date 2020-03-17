package org.springframework.samples.yogogym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.RoutineLine;

public interface RoutineLineRepository extends  CrudRepository<RoutineLine, String>{
	
}
