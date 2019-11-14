package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
