package org.springframework.samples.yogogym.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.DashboardClient;

public interface DashboardClientRepository extends CrudRepository<DashboardClient, Integer> {

	@Query("SELECT count(rl.exercise.bodyPart) FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() AND c.user.username = :username GROUP BY rl.exercise.bodyPart ORDER BY rl.exercise.bodyPart")
	List<Integer> countBodyPart(@Param("init") Date init, @Param("username") String username);

	@Query("SELECT rl.exercise.bodyPart FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() AND c.user.username = :username GROUP BY rl.exercise.bodyPart ORDER BY rl.exercise.bodyPart")
	List<String> nameBodyPart(@Param("init") Date init, @Param("username") String username);

	@Query("SELECT count(rl.exercise.repetitionType) FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() AND c.user.username = :username GROUP BY rl.exercise.repetitionType ORDER BY rl.exercise.repetitionType")
	List<Integer> countRepetitionType(@Param("init") Date init, @Param("username") String username);

	@Query("SELECT rl.exercise.repetitionType FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() AND c.user.username = :username GROUP BY rl.exercise.repetitionType ORDER BY rl.exercise.repetitionType")
	List<String> nameRepetitionType(@Param("init") Date init, @Param("username") String username);

	@Query("SELECT SUM(rl.exercise.kcal) FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE :init <= t.initialDate AND t.initialDate <= CURRENT_DATE() AND c.user.username = :username")
	Integer sumKcal(@Param("init") Date init, @Param("username") String username);

}
