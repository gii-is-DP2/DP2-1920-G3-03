package org.springframework.samples.yogogym.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Authorities;
import org.springframework.samples.yogogym.repository.AuthoritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository) {
		this.authoritiesRepository = authoritiesRepository;
	}
	
	@Transactional(readOnly=true)
	public Collection<Authorities> findAuthByUsername(String username) throws DataAccessException {
		return this.authoritiesRepository.findAuthByUsername(username);
	}

	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		this.authoritiesRepository.save(authorities);
	}
	
	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		authority.setUsername(username);
		authority.setAuthority(role);
		this.authoritiesRepository.save(authority);
	}
}
