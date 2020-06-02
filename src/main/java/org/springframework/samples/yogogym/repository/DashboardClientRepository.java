package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.DashboardClient;

public interface DashboardClientRepository extends CrudRepository<DashboardClient, Integer> {

	@Query("SELECT count(rl.exercise.bodyPart) FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month AND c.user.username = :username GROUP BY rl.exercise.bodyPart ORDER BY rl.exercise.bodyPart")
	Integer[] countBodyPart(@Param("month") int month, @Param("year") int year, @Param("username") String username);

	@Query("SELECT rl.exercise.bodyPart FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month AND c.user.username = :username GROUP BY rl.exercise.bodyPart ORDER BY rl.exercise.bodyPart")
	String[] nameBodyPart(@Param("month") int month, @Param("year") int year, @Param("username") String username);

	@Query("SELECT count(rl.exercise.repetitionType) FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month AND c.user.username = :username GROUP BY rl.exercise.repetitionType ORDER BY rl.exercise.repetitionType")
	Integer[] countRepetitionType(@Param("month") int month, @Param("year") int year, @Param("username") String username);

	@Query("SELECT rl.exercise.repetitionType FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month AND c.user.username = :username GROUP BY rl.exercise.repetitionType ORDER BY rl.exercise.repetitionType")
	String[] nameRepetitionType(@Param("month") int month, @Param("year") int year, @Param("username") String username);

	@Query("SELECT SUM(rl.exercise.kcal) FROM Client c left join c.trainings t left join t.routines r left join r.routineLine rl WHERE YEAR(t.endDate)=:year AND MONTH(t.endDate)=:month AND c.user.username = :username")
	Integer sumKcal(@Param("month") int month, @Param("year") int year, @Param("username") String username);

}
