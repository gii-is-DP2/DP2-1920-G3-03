package org.springframework.samples.yogogym.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.Diet;

public interface DietRepository extends  CrudRepository<Diet, Integer>{
	
    
}
