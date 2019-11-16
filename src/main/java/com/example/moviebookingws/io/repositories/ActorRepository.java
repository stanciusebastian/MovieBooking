package com.example.moviebookingws.io.repositories;

import com.example.moviebookingws.io.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorEntity,Long> {
    ActorEntity findByActorId(String actorId);
}
