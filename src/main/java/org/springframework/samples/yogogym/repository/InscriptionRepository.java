package org.springframework.samples.yogogym.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Inscription;

public interface InscriptionRepository extends  CrudRepository<Inscription, Integer>{

	@Query("SELECT inscription FROM Inscription inscription WHERE inscription.challenge.id=:id")
	Inscription findInscriptionByChallengeId(@Param("id") int challengeId);
	

}
