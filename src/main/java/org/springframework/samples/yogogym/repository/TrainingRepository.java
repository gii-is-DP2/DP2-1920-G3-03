package org.springframework.samples.yogogym.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;

public interface TrainingRepository extends  CrudRepository<Training, String>{
	
	@Query("SELECT client.trainings FROM Client client WHERE client.id=:id")
	public Collection<Training> findTrainingFromClient(@Param("id")int id);
	
	@Query("SELECT training FROM Training training WHERE training.id=:id")
	public Training findTrainingById(@Param("id")int id);
}
