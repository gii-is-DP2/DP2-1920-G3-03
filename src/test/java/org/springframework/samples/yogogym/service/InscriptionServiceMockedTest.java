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
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.repository.ClientRepository;
import org.springframework.samples.yogogym.repository.InscriptionRepository;

@ExtendWith(MockitoExtension.class)
public class InscriptionServiceMockedTest {

	@Mock
	private InscriptionRepository inscriptionRepository;
	
	protected InscriptionService inscriptionService;
	
	@BeforeEach
	void setup() {
		inscriptionService = new InscriptionService(inscriptionRepository);
	}
	
	@Test
	void shouldFindInscriptionByUsername() {
		Challenge sampleChallege = new Challenge();
		sampleChallege.setName("prueba");
		sampleChallege.setDescription("prueba");
		sampleChallege.setReward("prueba");
		sampleChallege.setPoints(3);
		Inscription sampleInscription = new Inscription();
		sampleInscription.setStatus(Status.COMPLETED);
		sampleInscription.setChallenge(sampleChallege);
		Client sampleClient = new Client();
		User sampleUser = new User();
		sampleUser.setUsername("prueba");
		sampleUser.setEnabled(true);
		List<Inscription> listSample = new ArrayList<Inscription>();
		listSample.add(sampleInscription);
		sampleClient.setInscriptions(listSample);
		sampleClient.setUser(sampleUser);
		when(inscriptionRepository.findIncriptionsByUsername(sampleUser.getUsername())).thenReturn(listSample);
		
		List<Inscription> listInscription = this.inscriptionService.findInscriptionsByUsername(sampleUser.getUsername());
		
		assertThat(listInscription).hasSize(1);
		Inscription i = listInscription.get(0);
		assertThat(i.getStatus()).isEqualTo(Status.COMPLETED);
		assertThat(i.getChallenge().getName()).isEqualTo("prueba");
	}
	
}
