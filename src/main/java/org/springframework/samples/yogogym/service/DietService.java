/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.yogogym.service;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.repository.DietRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class DietService {

	private DietRepository dietRepository;

	@Autowired
	public DietService(DietRepository dietRepository) {
		this.dietRepository = dietRepository;
	}
	
	@Transactional
	public void saveDiet(Diet Diet) throws DataAccessException {
		dietRepository.save(Diet);
	}
	
	@Transactional
	public Collection<Diet> findAllDiet() throws DataAccessException {
		
		Collection<Diet> res = new ArrayList<>();
		
		for(Diet d: this.dietRepository.findAll())
			res.add(d);
		
		return res;		
	}
	@Transactional
	public Diet findDietById(Integer dietId) throws DataAccessException {
		
		Diet res = this.dietRepository.findById(dietId).get();
		
		return res;		
	}
}
