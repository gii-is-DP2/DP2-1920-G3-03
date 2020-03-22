package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Trainer;

public interface TrainerRepository extends  CrudRepository<Trainer, Integer>{
	
	@Query("SELECT trainer FROM Trainer trainer WHERE trainer.user.username=:username")
	public Trainer findTrainer(@Param("username") String username);
	
}
