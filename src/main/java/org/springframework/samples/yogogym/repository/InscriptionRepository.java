package org.springframework.samples.yogogym.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.Inscription;

public interface InscriptionRepository extends  CrudRepository<Inscription, Integer>{

	@Query("SELECT i FROM Inscription i WHERE i.challenge.id=:challengeId")
	Collection<Inscription> findInscriptionsByChallengeId(int challengeId);

	@Query("SELECT i from Inscription i WHERE i.status=1")
	Collection<Inscription> findSubmittedInscriptions();
	
}
