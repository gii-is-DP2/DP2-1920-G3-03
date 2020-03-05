package org.springframework.samples.yogogym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.yogogym.model.User;


public interface UserRepository extends  CrudRepository<User, String>{
	
}
