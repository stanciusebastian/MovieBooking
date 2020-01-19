package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.ui.model.request.MovieScheduleDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.MovieScheduleRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("movies-schedule") //http://localhost::8080/movies-schedule
public class MovieScheduleController {
    @Autowired
    private MovieScheduleService movieScheduleService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/{scheduleId}")
    public MovieScheduleRest getMovieSchedule(@PathVariable String scheduleId) {
        MovieScheduleDto movieScheduleDto = movieScheduleService.getMovieScheduleByScheduleId(scheduleId);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(movieScheduleDto, movieScheduleRest);
        movieScheduleRest.setMovieId(movieScheduleDto.getMovie().getMovieId());
        return movieScheduleRest;
    }

    @PostMapping
    public MovieScheduleRest createMovieSchedule(@RequestBody MovieScheduleDetailsRequestModel movieScheduleDetailsRequestModel) {
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        MovieEntity movieEntity = new MovieEntity();
        MovieDto movieDto = movieService.getMovieByMovieId(movieScheduleDetailsRequestModel.getMovieId());
        BeanUtils.copyProperties(movieDto, movieEntity);
        movieScheduleDto.setHall(movieScheduleDetailsRequestModel.getHall());
        movieScheduleDto.setSchedule(movieScheduleDetailsRequestModel.getSchedule());
        movieScheduleDto.setCreatedAt(new Date());
        movieScheduleDto.setModifiedAt(new Date());
        movieScheduleDto.setMovie(movieEntity);
        MovieScheduleDto movieScheduleDto1 = movieScheduleService.createSchedule(movieScheduleDto);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(movieScheduleDto1, movieScheduleRest);
        movieScheduleRest.setMovieId(movieScheduleDto1.getMovie().getMovieId());
        return movieScheduleRest;
    }

    @PutMapping("/{scheduleId}")
    public MovieScheduleRest updateMovieSchedule(@PathVariable String scheduleId, @RequestBody MovieScheduleDetailsRequestModel movieScheduleDetailsRequestModel) {
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        MovieEntity movieEntity = new MovieEntity();
        MovieDto movieDto = movieService.getMovieByMovieId(movieScheduleDetailsRequestModel.getMovieId());
        BeanUtils.copyProperties(movieDto, movieEntity);
        BeanUtils.copyProperties(movieScheduleDetailsRequestModel, movieScheduleDto);
        movieScheduleDto.setMovie(movieEntity);
        movieScheduleDto.setHall(movieScheduleDetailsRequestModel.getHall());
        movieScheduleDto.setModifiedAt(new Date());
        movieScheduleDto.setSchedule(movieScheduleDetailsRequestModel.getSchedule());
        MovieScheduleDto scheduleDto = movieScheduleService.updateSchedule(scheduleId, movieScheduleDto);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(scheduleDto, movieScheduleRest);
        movieScheduleRest.setMovieId(scheduleDto.getMovie().getMovieId());
        return movieScheduleRest;
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable String scheduleId) {
        movieScheduleService.deleteSchedule(scheduleId);
    }
}
