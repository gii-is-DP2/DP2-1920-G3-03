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

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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

	private final Calendar now = Calendar.getInstance();

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
		for (Client c : clients) {
			c.setInscriptions(c.getInscriptions().stream().filter(i -> i.getStatus().equals(Status.SUBMITTED))
					.collect(Collectors.toList()));
		}
		return clients;
	}

	@Transactional
	public Collection<Client> findAllClient() throws DataAccessException {
		return this.clientRepository.findAll();

	}

	// Classification
	public List<String> classificationNameDate() {
		Calendar now2 = (Calendar) now.clone();
		Date d1 = new Date();
		Date d2 = new Date();
		d1 = now2.getTime();
		now2.add(Calendar.DAY_OF_MONTH, -7);
		d2 = now2.getTime();
		return this.clientRepository.classificationNameDate(d1, d2);
	}

	public List<Integer> classificationPointDate() {
		Calendar now2 = (Calendar) now.clone();
		Date d1 = new Date();
		Date d2 = new Date();
		d1 = now2.getTime();
		now2.add(Calendar.DAY_OF_MONTH, -7);
		d2 = now2.getTime();
		return this.clientRepository.classificationPointDate(d1, d2);
	}

	public List<String> classificationNameAll() {
		return this.clientRepository.classificationNameAll();
	}

	public List<Integer> classificationPointAll() {
		return this.clientRepository.classificationPointAll();
	}

}
