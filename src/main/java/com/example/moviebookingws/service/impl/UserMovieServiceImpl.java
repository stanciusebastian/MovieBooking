package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.UserMovieEntity;
import com.example.moviebookingws.io.repositories.UserMovieRepository;
import com.example.moviebookingws.service.UserMovieService;
import com.example.moviebookingws.shared.dto.UserMovieDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMovieServiceImpl implements UserMovieService {
    @Autowired
    private UserMovieRepository userMovieRepository;

    @Autowired
    private Utils utils;

    @Override
    public UserMovieDto createAttendee(UserMovieDto userMovieDto) {
        UserMovieEntity userMovieEntity = new UserMovieEntity();
        BeanUtils.copyProperties(userMovieDto, userMovieEntity);
        userMovieEntity.setAttendeeId(utils.generateAttendeeId(30));
        UserMovieEntity attendeeDetails = userMovieRepository.save(userMovieEntity);
        UserMovieDto attendee = new UserMovieDto();
        BeanUtils.copyProperties(userMovieEntity, attendee);

        return attendee;
    }
}
