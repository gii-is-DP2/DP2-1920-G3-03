package org.springframework.samples.yogogym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.Authorities;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
