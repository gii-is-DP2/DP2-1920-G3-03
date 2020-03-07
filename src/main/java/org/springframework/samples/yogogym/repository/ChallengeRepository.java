package org.springframework.samples.yogogym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.Challenge;

public interface ChallengeRepository extends CrudRepository<Challenge, Integer>{

	
}
