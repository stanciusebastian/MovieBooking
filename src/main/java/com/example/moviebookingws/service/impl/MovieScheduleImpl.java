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
import java.util.ArrayList;
import java.util.List;
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
        Optional<MovieEntity> movieEntity = movieRepository.findById(movieScheduleDto.getMovie().getId());
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

    @Override
    public List<MovieScheduleDto> getMoviesSchedules() {
        List<MovieScheduleEntity> movieScheduleEntities = movieScheduleRepository.findAll();
        List<MovieScheduleDto> movieScheduleDtos = new ArrayList<MovieScheduleDto>();
        for (MovieScheduleEntity movieScheduleEntity: movieScheduleEntities) {
            MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
            BeanUtils.copyProperties(movieScheduleEntity, movieScheduleDto);
            movieScheduleDtos.add(movieScheduleDto);
        }
        return movieScheduleDtos;
    }

    @Override
    public MovieScheduleDto getMovieScheduleById(long Id) {
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        Optional<MovieScheduleEntity> movieScheduleEntity = Optional.ofNullable(movieScheduleRepository.findById(Id));
        if (!movieScheduleEntity.isPresent())
            throw new EntityNotFoundException("id-" + Id);
        BeanUtils.copyProperties(movieScheduleEntity.get(),movieScheduleDto);
        return movieScheduleDto;
    }
}
