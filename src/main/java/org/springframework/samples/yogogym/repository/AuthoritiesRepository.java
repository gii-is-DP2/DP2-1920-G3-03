package org.springframework.samples.yogogym.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Authorities;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
	@Query("SELECT a from Authorities a WHERE a.username =:username")
	public Collection<Authorities> findAuthByUsername(@Param("username")String userName);
}
