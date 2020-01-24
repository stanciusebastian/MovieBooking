package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MovieScheduleRepository extends JpaRepository<MovieScheduleEntity, Long> {
    MovieScheduleEntity findByScheduleId(String scheduleId);
    MovieScheduleEntity findById(long Id);

    @Query(value = "SELECT * FROM  movie_schedule ms " +
            "WHERE DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:00') = DATE_SUB(DATE_FORMAT(ms.schedule, '%Y-%m-%d %H:%i'), INTERVAL 1 HOUR)", nativeQuery = true)
    Collection<MovieScheduleEntity> getScheduledMoviesAfterAnHour();

    @Query(value = "SELECT ms.* FROM movie_schedule ms INNER JOIN user_movie us ON us.movie_id = ms.id WHERE us.user_id = ? AND us.movie_id = ?", nativeQuery = true)
    MovieScheduleEntity findUserScheduledMovieByMovieId(long userId, long movieId);

    @Query(value = "SELECT ms.* FROM movie_schedule ms INNER JOIN user_movie us ON us.movie_id = ms.id WHERE us.user_id = ?", nativeQuery = true)
    Collection<MovieScheduleEntity> findUserBookedMovies(long userId);
}
