package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorEntity,Long> {
}
