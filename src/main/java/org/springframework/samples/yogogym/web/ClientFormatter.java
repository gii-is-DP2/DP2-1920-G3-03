package org.springframework.samples.yogogym.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.stereotype.Component;

@Component
public class ClientFormatter implements Formatter<Client>{
	
	private final ClientService clientService;
	
	@Autowired
	public ClientFormatter(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public String print(Client client, Locale locale) {
		return client.getNif();
	}

	@Override
	public Client parse(String text, Locale locale) throws ParseException {
		Collection<Client> findClients = this.clientService.findAllClient();
		for(Client client : findClients) {
			if(client.getNif().equals(text)) {
				return client;
			}
		}
		throw new ParseException("client not found: " + text, 0);
	}

}
