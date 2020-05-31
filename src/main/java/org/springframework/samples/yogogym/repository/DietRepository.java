package org.springframework.samples.yogogym.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Diet;


public interface DietRepository extends  CrudRepository<Diet, Integer>{
	
	@Query("select d from Diet d where d.id=:id")
	public Diet findDietById(@Param("id")int id);
    
}
