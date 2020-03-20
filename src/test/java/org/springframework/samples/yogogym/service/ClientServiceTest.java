package org.springframework.samples.yogogym.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Client;
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
	void shouldFindClientsWithOnlySubmittedInscriptions(){
		Collection<Client> clients = this.clientService.findClientsWithOnlySubmittedInscriptions();
		assertThat(clients.size()).isEqualTo(1);	
	}
	
	@Test
	void shouldFindClientsWithOnlyCompletedInscriptions() {
		List<Client> clients = this.clientService.findClientsWithCompletedInscriptions();
		assertThat(clients.size()).isEqualTo(1);
	}
	
}
