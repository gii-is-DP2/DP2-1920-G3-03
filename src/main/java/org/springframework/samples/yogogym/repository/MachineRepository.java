package org.springframework.samples.yogogym.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Machine;

public interface MachineRepository extends  CrudRepository<Machine, String>{
	
	@Query("SELECT machine FROM Machine machine WHERE machine.id=:id")
	public Machine findMachineByExerciseId(@Param("id") int id);
}
