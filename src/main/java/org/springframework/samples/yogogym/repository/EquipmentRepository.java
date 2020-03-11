package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Equipment;

public interface EquipmentRepository extends  CrudRepository<Equipment, String>{
	
	@Query("SELECT equipment FROM Equipment equipment WHERE equipment.id=:id")
	public Equipment findEquipmentByExerciseId(@Param("id") int id);
}
