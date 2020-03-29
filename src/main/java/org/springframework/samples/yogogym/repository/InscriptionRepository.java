package org.springframework.samples.yogogym.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Inscription;

public interface InscriptionRepository extends  CrudRepository<Inscription, Integer>{

	@Query("SELECT i FROM Inscription i WHERE i.challenge.id=:challengeId")
	Collection<Inscription> findInscriptionsByChallengeId(int challengeId);

	@Query("SELECT i from Inscription i WHERE i.status=1")
	Collection<Inscription> findSubmittedInscriptions();
	
	@Query("SELECT c.inscriptions FROM Client c WHERE c.user.username=:username")
	List<Inscription> findIncriptionsByUsername(@Param("username") String username);
	
}
