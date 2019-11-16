package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieScheduleRepository extends JpaRepository<MovieScheduleEntity,Long> {
    MovieScheduleEntity findByScheduleId(String scheduleId);
}
