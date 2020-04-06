package org.springframework.samples.yogogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Challenge;

public interface ChallengeRepository extends CrudRepository<Challenge, Integer>{

	@Query("SELECT i.challenge from Inscription i WHERE i.status=1")
	List<Challenge> findSubmittedChallenges();
	
	//Clasification
	@Query("SELECT i.challenge FROM Client c left join c.inscriptions i WHERE c.user.username=:username AND i.status = 2")
	List<Challenge> findChallengesByUsername(@Param("username") String username);
	
	@Query("SELECT SUM(i.challenge.points) FROM Client c left join c.inscriptions i WHERE c.user.username=:username AND i.status = 2")
	Integer sumPointChallengesByUsername(@Param("username") String username);

}
