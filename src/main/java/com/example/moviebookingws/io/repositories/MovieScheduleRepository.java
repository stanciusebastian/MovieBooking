package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MovieScheduleRepository extends JpaRepository<MovieScheduleEntity, Long> {
    MovieScheduleEntity findByScheduleId(String scheduleId);

    @Query(value = "SELECT * FROM  movie_schedule ms " +
            "WHERE DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:00') = DATE_SUB(DATE_FORMAT(ms.schedule, '%Y-%m-%d %H:%i'), INTERVAL 1 HOUR)", nativeQuery = true)
    Collection<MovieScheduleEntity> getScheduledMoviesAfterAnHour();
}
