package org.springframework.samples.yogogym.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.DashboardAdmin;
import org.springframework.samples.yogogym.model.Inscription;

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

	// General info about the gym
	@Query("SELECT count(c) FROM Client c")
	Integer countClients();

	@Query("SELECT count(t) FROM Trainer t")
	Integer countTrainers();

	@Query("SELECT count(c) FROM Client c GROUP BY c.guild ")
	List<Integer> countClientsPerGuild();
}
