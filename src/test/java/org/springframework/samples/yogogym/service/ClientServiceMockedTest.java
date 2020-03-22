package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientServiceMockedTest {
	
	@Mock
	private ClientRepository clientRepository;
	
	protected ClientService clientService;
	
	@BeforeEach
	void setup() {
		clientService = new ClientService(clientRepository);
	}
	
	@Test
	void shouldFindClientsWithCompletedInscriptions() {
		Challenge sampleChallege = new Challenge();
		sampleChallege.setName("prueba");
		sampleChallege.setDescription("prueba");
		sampleChallege.setReward("prueba");
		sampleChallege.setPoints(3);
		Inscription sampleInscription = new Inscription();
		sampleInscription.setStatus(Status.COMPLETED);
		sampleInscription.setChallenge(sampleChallege);
		Client sampleClient = new Client();
		List<Inscription> listSample = new ArrayList<Inscription>();
		listSample.add(sampleInscription);
		sampleClient.setInscriptions(listSample);
		List<Client> listSampleClient = new ArrayList<Client>();
		listSampleClient.add(sampleClient);
		when(clientRepository.findClientsWithCompletedInscriptions()).thenReturn(listSampleClient);
		
		List<Client> listClient = this.clientService.findClientsWithCompletedInscriptions();
		
		assertThat(listClient).hasSize(1);
		Client c = listClient.get(0);
		assertThat(c.getInscriptions().get(0).getStatus()).isEqualTo(Status.COMPLETED);
		assertThat(c.getInscriptions().get(0).getChallenge().getName()).isEqualTo("prueba");
	}

}
