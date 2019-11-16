package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.repositories.MovieScheduleRepository;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieScheduleImpl implements MovieScheduleService {
    @Autowired
    private MovieScheduleRepository movieScheduleRepository;

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
}
