package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

	private final Calendar now = Calendar.getInstance(); 

	private ClientRepository clientRepository;

	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	
	@CacheEvict(cacheNames = "percentageClients", allEntries = true)
	@Transactional
	public void saveClient(Client client) throws DataAccessException {
		this.clientRepository.save(client);
	}

	@Transactional(readOnly=true)
	public Client findClientById(int clientId) throws DataAccessException {
		return this.clientRepository.findClientById(clientId);
	}

	@Transactional(readOnly=true)
	public Client findClientByInscriptionId(int inscriptionId) {
		return this.clientRepository.findClientByInscriptionId(inscriptionId);
	}

	@Transactional(readOnly=true)
	public Client findClientByUsername(String username) {
		return this.clientRepository.findClientByUsername(username);
	}

	@Transactional(readOnly=true)
	public List<Client> findClientsWithOnlySubmittedInscriptions() {

		List<Client> clients = this.clientRepository.findClientsWithSubmittedInscriptions();
		for (Client c : clients) {
			c.setInscriptions(c.getInscriptions().stream().filter(i -> i.getStatus().equals(Status.SUBMITTED))
					.collect(Collectors.toList()));
		}
		return clients;
	}

	@Transactional(readOnly=true)
	public Collection<Client> findAllClient() throws DataAccessException {
		return this.clientRepository.findAll();

	}
	
	@Transactional(readOnly=true)
	public Boolean isPublicByTrainingId(int trainingId) throws DataAccessException{
		return this.clientRepository.isPublic(trainingId);
	}

	// Classification
	@Transactional(readOnly=true)
	public List<String> classificationNameDate() {
		Calendar now2 = (Calendar) now.clone();
		Date d1 = new Date();
		Date d2 = new Date();
		d1 = now2.getTime();
		now2.add(Calendar.DAY_OF_MONTH, -7);
		d2 = now2.getTime();
		return this.clientRepository.classificationNameDate(d2, d1);
	}

	@Transactional(readOnly=true)
	public List<Integer> classificationPointDate() {
		Calendar now2 = (Calendar) now.clone();
		Date d1 = new Date();
		Date d2 = new Date();
		d1 = now2.getTime();
		now2.add(Calendar.DAY_OF_MONTH, -7);
		d2 = now2.getTime();
		return this.clientRepository.classificationPointDate(d2, d1);
	}

	@Transactional(readOnly=true)
	public List<String> classificationNameAll() {
		return this.clientRepository.classificationNameAll();
	}

	@Transactional(readOnly=true)
	public List<Integer> classificationPointAll() {
		return this.clientRepository.classificationPointAll();
	}

}
