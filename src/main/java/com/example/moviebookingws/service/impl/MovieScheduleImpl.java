package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.repositories.MovieRepository;
import com.example.moviebookingws.io.repositories.MovieScheduleRepository;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class MovieScheduleImpl implements MovieScheduleService {
    @Autowired
    private MovieScheduleRepository movieScheduleRepository;

    @Autowired
    private MovieRepository movieRepository;


    @Autowired
    private Utils utils;

    @Override
    public MovieScheduleDto createSchedule(MovieScheduleDto movieScheduleDto) {
        MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();
        BeanUtils.copyProperties(movieScheduleDto, movieScheduleEntity);
        movieScheduleEntity.setScheduleId(utils.generateMovieScheduleId(30));
        MovieScheduleEntity movieScheduleDetails = movieScheduleRepository.save(movieScheduleEntity);
        MovieScheduleDto movieSchedule = new MovieScheduleDto();
        BeanUtils.copyProperties(movieScheduleDetails, movieSchedule);

        return movieSchedule;
    }

    @Override
    public MovieScheduleDto getMovieScheduleByScheduleId(String scheduleId) {
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        Optional<MovieScheduleEntity> movieScheduleEntity  = Optional.ofNullable(movieScheduleRepository.findByScheduleId(scheduleId));
        if (!movieScheduleEntity.isPresent())
            throw new EntityNotFoundException("id-" + scheduleId);
        BeanUtils.copyProperties(movieScheduleEntity.get(),movieScheduleDto);
        return movieScheduleDto;
    }

    @Override
    public MovieScheduleDto updateSchedule(String scheduleId, MovieScheduleDto movieScheduleDto) {
        MovieScheduleEntity movieScheduleEntity = movieScheduleRepository.findByScheduleId(scheduleId);
        Optional<MovieEntity> movieEntity = movieRepository.findById(movieScheduleDto.getMovieId());
        if (movieScheduleEntity==null) {
            throw new EntityNotFoundException("ScheduleId-" + scheduleId);
        }
        movieScheduleEntity.setHall(movieScheduleDto.getHall());
        movieScheduleEntity.setSchedule(movieScheduleDto.getSchedule());
        movieScheduleEntity.setMovieSchedule(movieEntity.get());
        MovieScheduleEntity movieScheduleEntity1 = movieScheduleRepository.save(movieScheduleEntity);
        MovieScheduleDto movieScheduleDto1 = new MovieScheduleDto();
        BeanUtils.copyProperties(movieScheduleEntity1, movieScheduleDto1);
        return movieScheduleDto1;
    }

    @Override
    public void deleteSchedule(String scheduleId) {
        MovieScheduleEntity movieScheduleEntity = movieScheduleRepository.findByScheduleId(scheduleId);
        if (movieScheduleEntity==null)
            throw new EntityNotFoundException("id-" + scheduleId);
        movieScheduleRepository.delete(movieScheduleEntity);
    }
}
