package org.springframework.samples.yogogym.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Client;

public interface ClientRepository extends  CrudRepository<Client, String>{
	
	@Query("SELECT client FROM Client client WHERE client.id=:id")
	public Client findClientById(@Param("id") int id);
	
	@Query("SELECT client FROM Client client")
	public Collection<Client> findAll();

	@Query("SELECT c FROM Client c LEFT JOIN c.inscriptions i WHERE i.id=:id")
	public Client findClientByInscriptionId(@Param("id") int inscriptionId);
	
	@Query("SELECT c FROM Client c WHERE c.user.username=:username")
	public Client findClientByUsername(@Param("username") String username);
	
	@Query("Select c FROM Client c WHERE c.user.username=:clientUsername")
	public Client findClientByClientUsername(String clientUsername);

	@Query("SELECT DISTINCT c FROM Client c LEFT JOIN c.inscriptions i WHERE i.status=1")
	public List<Client> findClientsWithSubmittedInscriptions();
	
	//Clasification
	@Query("SELECT c.user.username FROM Client c left join c.inscriptions i WHERE i.status = 2 AND (i.challenge.initialDate BETWEEN :dateBefore AND :dateAfter) GROUP BY c.user.username ORDER BY SUM(i.challenge.points) DESC")
	List<String> classificationNameDate(@Param("dateBefore") Date dateBefore, @Param("dateAfter") Date dateAfter);
	
	@Query("SELECT SUM(i.challenge.points) FROM Client c left join c.inscriptions i WHERE i.status = 2 AND (i.challenge.initialDate BETWEEN :dateBefore AND :dateAfter) GROUP BY c.user.username ORDER BY SUM(i.challenge.points) DESC")
	List<Integer> classificationPointDate(@Param("dateBefore") Date dateBefore, @Param("dateAfter") Date dateAfter);
	
	@Query("SELECT c.user.username FROM Client c left join c.inscriptions i WHERE i.status = 2 GROUP BY c.user.username ORDER BY SUM(i.challenge.points) DESC")
	List<String> classificationNameAll();
	
	@Query("SELECT SUM(i.challenge.points) FROM Client c left join c.inscriptions i WHERE i.status = 2 GROUP BY c.user.username ORDER BY SUM(i.challenge.points) DESC")
	List<Integer> classificationPointAll();

}
