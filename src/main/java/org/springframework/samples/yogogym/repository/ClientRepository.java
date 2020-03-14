package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Client;

public interface ClientRepository extends  CrudRepository<Client, String>{
	
	@Query("SELECT client FROM Client client WHERE client.id=:id")
	public Client findClientById(@Param("id") int id);

	@Query("SELECT c FROM Client c LEFT JOIN c.inscriptions i WHERE i.id=:id")
	public Client findClientByInscriptionId(@Param("id") int inscriptionId);
}
