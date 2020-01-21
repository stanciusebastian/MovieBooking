package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.entity.UserMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserMovieRepository extends JpaRepository<UserMovieEntity, Long> {
    UserMovieEntity findByAttendeeId(String attendeeId);

    @Query(value = "SELECT * FROM user_movie us WHERE us.user_id = ? AND us.movie_id = ?", nativeQuery = true)
    UserMovieEntity findUserMovieByUserIdAndMovieId(long userId, long movieId);
}
