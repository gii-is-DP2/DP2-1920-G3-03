package org.springframework.samples.yogogym.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.DashboardAdmin;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageClients;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageGuilds;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;

public interface DashboardsAdminRepository extends CrudRepository<DashboardAdmin, Integer> {

	/* Equipment control */

	@Query("SELECT count(rl.exercise.equipment) FROM Training t left join t.routines r left join r.routineLine rl WHERE (YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month) GROUP BY rl.exercise.equipment ORDER BY rl.exercise.equipment.name")
	Integer[] countEquipment(@Param("month") int month, @Param("year") int year);

	@Query("SELECT rl.exercise.equipment.name FROM Training t left join t.routines r left join r.routineLine rl WHERE (YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month) GROUP BY rl.exercise.equipment ORDER BY rl.exercise.equipment.name")
	String[] nameEquipment(@Param("month") int month, @Param("year") int year);
	

	
	// Dashboard Challenges
	
	@Query("SELECT COUNT(c) FROM Challenge c WHERE YEAR(c.endDate)=:year AND MONTH(c.endDate)=:month")
	int countChallengesOfMonthAndYear(int month, int year);
	
	@Query("SELECT count(i) FROM Inscription i WHERE YEAR(i.challenge.endDate)=:year AND MONTH(i.challenge.endDate)=:month AND i.status=2")
	int countCompletedInscriptionsOfMonthAndYear(int month, int year);
	
	@Query("SELECT c.name FROM Challenge c WHERE YEAR(c.endDate)=:year AND MONTH(c.endDate)=:month ORDER BY c.name ASC")
	String[] getChallengesNamesOfMonthAndYear(int month, int year);
	
	@Query("SELECT CONCAT(c.firstName,' ', c.lastName) AS username, c.email AS email, SUM(ch.points) AS points FROM Client c "
			+ "INNER JOIN c.inscriptions i INNER JOIN i.challenge ch WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2) "
			+ "GROUP BY c ORDER BY points DESC")
	List<DashboardAdminChallengesTopClient> getTopClient(Integer month, Integer year, Pageable pageable);
	
	@Query("SELECT g.name AS guild, SUM(ch.points) AS points FROM Client c INNER JOIN c.inscriptions i INNER JOIN i.challenge "
			+ "ch INNER JOIN c.guild g WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2)"
			+ " GROUP BY g ORDER BY points DESC")
	List<DashboardAdminChallengesTopGuild> getTopGuild(Integer month, Integer year, Pageable pageable);
	
	@Query("SELECT COUNT(i)/(SELECT (count(c)*1.0) FROM Client c) AS percentageClients, ch.name AS challengeName"
			+ " FROM Inscription i "
			+ "INNER JOIN i.challenge ch WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2) "
			+ "GROUP BY ch ORDER BY ch.name ASC")
	List<DashboardAdminChallengesPercentageClients> getPercentageClients(int month, int year);
	
	@Query("SELECT COUNT(DISTINCT c.guild)/(SELECT (count(g)*1.0) FROM Guild g) AS percentageGuilds, ch.name AS challengeName"
			+ " FROM Client c INNER JOIN c.inscriptions i "
			+ "INNER JOIN i.challenge ch WHERE (YEAR(ch.endDate)=:year AND MONTH(ch.endDate)=:month AND i.status=2) "
			+ "GROUP BY ch ORDER BY ch.name ASC")
	List<DashboardAdminChallengesPercentageGuilds> getPercentageGuilds(int month, int year);
	
	
	// Dashboard General
	
	@Query("SELECT count(c) FROM Client c")
	Integer countClients();

	@Query("SELECT count(t) FROM Trainer t")
	Integer countTrainers();

	@Query("SELECT count(c) FROM Client c GROUP BY c.guild ")
	List<Integer> countClientsPerGuild();

}
