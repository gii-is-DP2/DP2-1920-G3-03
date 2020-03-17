package org.springframework.samples.yogogym.service;



import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClientServiceTest {

	@Autowired
	protected ClientService clientService;
	
	@Test
	void shouldFindClientById(){
		Client client = this.clientService.findClientById(1);
		assertThat(client.getNif()).isEqualTo("12345678B");	
	}

	@Test
	void shouldFindClientByInscriptionId(){
		Client client = this.clientService.findClientByInscriptionId(2);
		assertThat(client.getNif()).isEqualTo("12345678B");	
	}
	
	@Test
	void shouldFindClientsWithSubmittedInscriptions(){
		Collection<Client> clients = this.clientService.findClientsWithSubmittedInscriptions();
		assertThat(clients.size()).isEqualTo(1);	
	}
	
}
