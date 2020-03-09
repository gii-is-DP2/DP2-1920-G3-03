package org.springframework.samples.yogogym.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Trainer;

public interface TrainerRepository extends  CrudRepository<Trainer, String>{
	
	@Query("SELECT trainer FROM Trainer trainer WHERE trainer.user.username=:username")
	public Trainer findTrainer(@Param("username") String username);
	
}
