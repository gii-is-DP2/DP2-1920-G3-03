package org.springframework.samples.yogogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.Challenge;

public interface ChallengeRepository extends CrudRepository<Challenge, Integer>{

	@Query("SELECT i.challenge from Inscription i WHERE i.status=1")
	List<Challenge> findSubmittedChallenges();
	

}
