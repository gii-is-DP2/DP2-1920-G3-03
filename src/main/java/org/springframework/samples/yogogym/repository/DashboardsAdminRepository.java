package org.springframework.samples.yogogym.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.DashboardAdmin;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;

public interface DashboardsAdminRepository extends CrudRepository<DashboardAdmin, Integer> {

	/* Equipment control */

	@Query("SELECT count(rl.exercise.equipment) FROM Training t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() GROUP BY rl.exercise.equipment ORDER BY rl.exercise.equipment.name")
	List<Integer> countEquipment(@Param("init") Date init);

	@Query("SELECT rl.exercise.equipment.name FROM Training t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() GROUP BY rl.exercise.equipment ORDER BY rl.exercise.equipment.name")
	List<String> nameEquipment(@Param("init") Date init);
	

	@Query("SELECT c FROM Challenge c WHERE YEAR(c.endDate)=:year AND MONTH(c.endDate)=:month")
	Collection<Challenge> findChallengesByMonthAndYear(Integer month, Integer year);
	
	@Query("SELECT i FROM Inscription i WHERE YEAR(i.challenge.endDate)=:year AND MONTH(i.challenge.endDate)=:month AND i.status=2")
	List<Inscription> findCompletedInscriptionsByMonthAndYear(int month, int year);
	
	// LO NUEVO
	
	@Query("SELECT count(i) FROM Inscription i WHERE YEAR(i.challenge.endDate)=:year AND MONTH(i.challenge.endDate)=:month AND i.status=2")
	int countCompletedInscriptionsOfMonthAndYear(int month, int year);
	
	@Query("SELECT CONCAT(c.firstName,' ', c.lastName) AS username, c.email AS email, SUM(ch.points) AS points FROM Client c "
			+ "INNER JOIN c.inscriptions i INNER JOIN i.challenge ch WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2) "
			+ "GROUP BY c ORDER BY points DESC")
	List<DashboardAdminChallengesTopClient> findTopClient(Integer month, Integer year, Pageable pageable);
	
	@Query("SELECT g.name AS guild, SUM(ch.points) AS points FROM Client c INNER JOIN c.inscriptions i INNER JOIN i.challenge "
			+ "ch INNER JOIN c.guild g WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2)"
			+ " GROUP BY g ORDER BY points DESC")
	List<DashboardAdminChallengesTopGuild> findTopGuild(Integer month, Integer year, Pageable pageable);
	
	
	@Query("SELECT c.name FROM Challenge c WHERE YEAR(c.endDate)=:year AND MONTH(c.endDate)=:month ORDER BY c.name ASC")
	String[] getChallengesNames(int month, int year);
	
	@Query("SELECT COUNT(i)/(SELECT (count(c)*1.0) FROM Client c) FROM Inscription i "
			+ "INNER JOIN i.challenge ch WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2) "
			+ "GROUP BY ch ORDER BY ch.name ASC")
	Double[] getPercentageClients(int month, int year);
	
	@Query("SELECT COUNT(DISTINCT c.guild)/(SELECT (count(g)*1.0) FROM Guild g) FROM Client c INNER JOIN c.inscriptions i "
			+ "INNER JOIN i.challenge ch WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2) "
			+ "GROUP BY ch ORDER BY ch.name ASC")
	Double[] getPercentageGuilds(int month, int year);
	
	// YA NO NUEVO

	// General info about the gym
	@Query("SELECT count(c) FROM Client c")
	Integer countClients();

	@Query("SELECT count(t) FROM Trainer t")
	Integer countTrainers();

	@Query("SELECT count(c) FROM Client c GROUP BY c.guild ")
	List<Integer> countClientsPerGuild();


}
