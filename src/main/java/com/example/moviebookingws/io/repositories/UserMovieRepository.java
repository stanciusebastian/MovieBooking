package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.UserMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovieRepository extends JpaRepository<UserMovieEntity,Long> {
}
