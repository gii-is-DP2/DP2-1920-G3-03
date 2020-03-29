package org.springframework.samples.yogogym.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Training;

public interface TrainingRepository extends  CrudRepository<Training, String>{
	
	@Query("SELECT client.trainings FROM Client client WHERE client.id=:id")
	public Collection<Training> findTrainingFromClient(@Param("id")int id);
	
	@Query("SELECT training FROM Training training WHERE training.id=:id")
	public Training findTrainingById(@Param("id")int id);
	
	@Query("SELECT training FROM Training training WHERE training.client.id=:clientId AND training.id!=:trainingId AND (:init BETWEEN training.initialDate AND training.endDate) ORDER BY training.initialDate ASC")
	public Collection<Training> countConcurrentTrainingsForInit(@Param("clientId") int clientId, @Param("trainingId") int trainingId, @Param("init") Date init);
	
	@Query("SELECT training FROM Training training WHERE training.client.id=:clientId AND training.id!=:trainingId AND (:end BETWEEN training.initialDate AND training.endDate) ORDER BY training.initialDate ASC")
	public Collection<Training> countConcurrentTrainingsForEnd(@Param("clientId") int clientId, @Param("trainingId") int trainingId, @Param("end") Date end);
	
	@Query("SELECT training FROM Training training WHERE training.client.id=:clientId AND training.id!=:trainingId AND ((training.initialDate BETWEEN :init AND :end) AND (training.endDate BETWEEN :init AND :end)) ORDER BY training.initialDate ASC")
	public Collection<Training> countConcurrentTrainingsForIncluding(@Param("clientId") int clientId, @Param("trainingId") int trainingId, @Param("init") Date init, @Param("end") Date end);

}
	
