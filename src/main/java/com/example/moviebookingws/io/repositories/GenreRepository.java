package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
}
