package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.ui.model.request.MovieScheduleDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.ActorRest;
import com.example.moviebookingws.ui.model.response.MovieRest;
import com.example.moviebookingws.ui.model.response.MovieScheduleRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("movies-schedule") //http://localhost::8080/movies-schedule
public class MovieScheduleController {
    @Autowired
    private MovieScheduleService movieScheduleService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieScheduleRest> getMoviesSchedules() {
        List<MovieScheduleDto> movieScheduleDtos = movieScheduleService.getMoviesSchedules();
        List<MovieScheduleRest> movieScheduleRests = new ArrayList<MovieScheduleRest>();
        for (MovieScheduleDto movieScheduleDto: movieScheduleDtos) {
            MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
            BeanUtils.copyProperties(movieScheduleDto, movieScheduleRest);
            MovieRest movieRest = new MovieRest();
            BeanUtils.copyProperties(movieScheduleDto.getMovie(), movieRest);
            ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
            for (ActorEntity actorEntity: movieScheduleDto.getMovie().getPlayedActors()) {
                ActorRest actorRest = new ActorRest();
                BeanUtils.copyProperties(actorEntity,actorRest);
                actors.add(actorRest);
            }
            movieRest.setActors(actors);
            movieRest.setGenreId(movieScheduleDto.getMovie().getGenre().getGenreId());
            movieScheduleRest.setMovie(movieRest);
            movieScheduleRests.add(movieScheduleRest);
        }
        return movieScheduleRests;
    }

    @GetMapping("/{scheduleId}")
    public MovieScheduleRest getMovieSchedule(@PathVariable String scheduleId) {
        MovieScheduleDto movieScheduleDto = movieScheduleService.getMovieScheduleByScheduleId(scheduleId);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(movieScheduleDto, movieScheduleRest);
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movieScheduleDto.getMovie(), movieRest);
        ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
        for (ActorEntity actorEntity: movieScheduleDto.getMovie().getPlayedActors()) {
            ActorRest actorRest = new ActorRest();
            BeanUtils.copyProperties(actorEntity,actorRest);
            actors.add(actorRest);
        }
        movieRest.setActors(actors);
        movieRest.setGenreId(movieScheduleDto.getMovie().getGenre().getGenreId());
        movieScheduleRest.setMovie(movieRest);
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
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movieScheduleDto1.getMovie(), movieRest);
        movieRest.setGenreId(movieScheduleDto1.getMovie().getMovieId());
        ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
        for (ActorEntity actorEntity: movieScheduleDto1.getMovie().getPlayedActors()) {
            ActorRest actorRest = new ActorRest();
            BeanUtils.copyProperties(actorEntity,actorRest);
            actors.add(actorRest);
        }
        movieRest.setActors(actors);
        movieScheduleRest.setMovie(movieRest);
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
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(scheduleDto.getMovie(), movieRest);
        movieRest.setGenreId(scheduleDto.getMovie().getMovieId());
        ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
        for (ActorEntity actorEntity: scheduleDto.getMovie().getPlayedActors()) {
            ActorRest actorRest = new ActorRest();
            BeanUtils.copyProperties(actorEntity,actorRest);
            actors.add(actorRest);
        }
        movieRest.setActors(actors);
        movieScheduleRest.setMovie(movieRest);
        return movieScheduleRest;
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable String scheduleId) {
        movieScheduleService.deleteSchedule(scheduleId);
    }
}
