package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.entity.UserEntity;
import com.example.moviebookingws.io.entity.UserMovieEntity;
import com.example.moviebookingws.io.repositories.MovieScheduleRepository;
import com.example.moviebookingws.io.repositories.UserMovieRepository;
import com.example.moviebookingws.io.repositories.UserRepository;
import com.example.moviebookingws.service.UserMovieService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.UserMovieDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class UserMovieServiceImpl implements UserMovieService {
    @Autowired
    private UserMovieRepository userMovieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieScheduleRepository movieScheduleRepository;

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

    @Override
    public MovieScheduleDto getUserScheduledMovieByMovieId(String userId, String scheduleId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        MovieScheduleEntity movieScheduleEntity = movieScheduleRepository.findByScheduleId(scheduleId);
        MovieScheduleEntity schedule = movieScheduleRepository.findUserScheduledMovieByMovieId(userEntity.getId(), movieScheduleEntity.getId());
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        BeanUtils.copyProperties(schedule, movieScheduleDto);
        return movieScheduleDto;
    }

    @Override
    public Collection<MovieScheduleDto> getUserBookedMovies(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        Collection<MovieScheduleEntity> movieScheduleEntities  = movieScheduleRepository.findUserBookedMovies(userEntity.getId());
        Collection<MovieScheduleDto> movieScheduleDtos = new HashSet<MovieScheduleDto>();
        for (MovieScheduleEntity movieScheduleEntity: movieScheduleEntities) {
            MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
            BeanUtils.copyProperties(movieScheduleEntity, movieScheduleDto);
            movieScheduleDtos.add(movieScheduleDto);
        }
        return movieScheduleDtos;
    }

    @Override
    public long getUserMovieRating(String userId, String scheduleId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        MovieScheduleEntity movieScheduleEntity = movieScheduleRepository.findByScheduleId(scheduleId);
        UserMovieEntity userMovieEntity = userMovieRepository.findUserMovieByUserIdAndMovieId(userEntity.getId(), movieScheduleEntity.getId());
        return userMovieEntity.getRating();
    }
}
