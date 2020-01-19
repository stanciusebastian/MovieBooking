package com.example.moviebookingws.service;

import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.shared.dto.ActorDto;

import java.util.Set;

public interface ActorService  {
    ActorDto createActor(ActorDto actorDto);
    ActorDto getActorByActorId(String actorId);
    Set<ActorDto> getActorsByActorsIds(String[] actorsIds);
}
