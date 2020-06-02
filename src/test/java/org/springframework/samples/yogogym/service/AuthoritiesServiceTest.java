package org.springframework.samples.yogogym.service;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Authorities;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthoritiesServiceTest {
	
	private final String username = "client1";
	private final String role = "trainer";
	
	@Autowired
	private AuthoritiesService authoritiesService;
		
	@ParameterizedTest
	@ValueSource(strings = {"client1", "trainer1"})
	void findAllAuthoritiesFromUsername(String client)
	{
		Collection<Authorities> authorities = authoritiesService.findAuthByUsername(client);
		
		Boolean checkNotNull = authorities != null;
		Boolean checkNotEmpty = !authorities.isEmpty();
		Boolean checkSize = authorities.size() == 1;
		Boolean checkAuth = false;
		
		if(client.equals("client1"))
			checkAuth = authorities.stream().collect(Collectors.toList()).get(0).getAuthority().equals("client");
		else if(client.equals("trainer1"))
			checkAuth = authorities.stream().collect(Collectors.toList()).get(0).getAuthority().equals("trainer");
		
		assertTrue(checkNotNull);
		assertTrue(checkNotEmpty);
		assertTrue(checkSize);
		assertTrue(checkAuth);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void saveAuthority()
	{
		Collection<Authorities> authorities = authoritiesService.findAuthByUsername(username);
		int sizeBefore = authorities.size();
		
		this.authoritiesService.saveAuthorities(username, role);
		
		authorities = authoritiesService.findAuthByUsername(username);
		int sizeAfter = authorities.size();
		
		Boolean checkNotNull = authorities != null;
		Boolean checkNotEmpty = !authorities.isEmpty();
		Boolean checkSize = authorities.size() == 2;
		Boolean checkIncrement = sizeBefore < sizeAfter;
		Boolean checkRole = authorities.stream().map(x->x.getAuthority()).collect(Collectors.toList()).contains(role);		
		
		assertTrue(checkNotNull);
		assertTrue(checkNotEmpty);
		assertTrue(checkSize);
		assertTrue(checkIncrement);
		assertTrue(checkRole);
	}


}
