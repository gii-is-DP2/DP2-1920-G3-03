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
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Machine;
import org.springframework.samples.yogogym.repository.ExerciseRepository;
import org.springframework.samples.yogogym.repository.MachineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class MachineService {

	private MachineRepository machineRepository;

	@Autowired
	public MachineService(MachineRepository machineRepository) {
		this.machineRepository = machineRepository;
	}
	
	@Transactional
	public void saveMachine(Machine machine) throws DataAccessException {
		machineRepository.save(machine);
	}
	
	@Transactional
	public Collection<Machine> findAllMachine() throws DataAccessException {
		
		Collection<Machine> res = new ArrayList<>();
		
		for(Machine m: this.machineRepository.findAll())
			res.add(m);
		
		return res;		
	}
	
	@Transactional
	public Machine findMachineByExerciseId(int exerciseId) throws DataAccessException {
		
		Machine res = this.machineRepository.findMachineByExerciseId(exerciseId);
		
		return res;		
	}
}
