package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.repositories.ActorRepository;
import com.example.moviebookingws.service.ActorService;
import com.example.moviebookingws.shared.dto.ActorDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
