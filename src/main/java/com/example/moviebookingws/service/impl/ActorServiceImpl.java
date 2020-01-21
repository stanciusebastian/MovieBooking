package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.repositories.ActorRepository;
import com.example.moviebookingws.service.ActorService;
import com.example.moviebookingws.shared.dto.ActorDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ActorServiceImpl  implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private Utils utils;

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        ActorEntity actorEntity = new ActorEntity();
        BeanUtils.copyProperties(actorDto,actorEntity);
        actorEntity.setActorId(utils.generateActorId(30));
        ActorEntity actor = actorRepository.save(actorEntity);
        ActorDto actorDetails = new ActorDto();
        BeanUtils.copyProperties(actorEntity,actorDetails);

        return actorDetails;
    }

    @Override
    public ActorDto getActorByActorId(String actorId) {
        ActorDto actor = new ActorDto();
        Optional<ActorEntity> actorEntityOptional  = Optional.ofNullable(actorRepository.findByActorId(actorId));
        if (!actorEntityOptional.isPresent())
            throw new EntityNotFoundException("id-" + actorId);
        BeanUtils.copyProperties(actorEntityOptional.get(),actor);
        return actor;
    }

    @Override
    public Set<ActorDto> getActorsByActorsIds(String[] actorsIds) {
        Set<ActorDto> actorDtoSet = new HashSet<ActorDto>();
        for (String actorId : actorsIds) {
            Optional<ActorEntity> actorEntityOptional  = Optional.ofNullable(actorRepository.findByActorId(actorId));
            ActorDto actorDto = new ActorDto();
            BeanUtils.copyProperties(actorEntityOptional.get(),actorDto);
            actorDtoSet.add(actorDto);
        }
        return actorDtoSet;
    }
}
