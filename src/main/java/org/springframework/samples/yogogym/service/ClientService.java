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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Transactional
	public void saveClient(Client client) throws DataAccessException {
		clientRepository.save(client);
	}
	
	@Transactional
	public Client findClientById(int clientId) throws DataAccessException {
		
		Client res = this.clientRepository.findClientById(clientId);
		
		return res;		
	}

	@Transactional
	public Client findClientByInscriptionId(int inscriptionId) {
		
		return this.clientRepository.findClientByInscriptionId(inscriptionId);
	}
	
	@Transactional
	public Client findClientByUsername(String username) {
		return this.clientRepository.findClientByUsername(username);
	}

	public List<Client> findClientsWithOnlySubmittedInscriptions() {
		
		List<Client> clients = this.clientRepository.findClientsWithSubmittedInscriptions();
		for(Client c : clients) {
			c.setInscriptions(c.getInscriptions().stream().filter(i -> i.getStatus().equals(Status.SUBMITTED)).collect(Collectors.toList()));
		}
		return clients;
	}

	
	@Transactional
	public Collection<Client> findAllClient() throws DataAccessException {
		return this.clientRepository.findAll();

	}
	
	//Clasication
	@Transactional
	public List<Client> findClientsWithCompletedInscriptions(){
		return this.clientRepository.findClientsWithCompletedInscriptions();
	}

	
}
