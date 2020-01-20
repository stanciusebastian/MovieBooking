package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.entity.UserEntity;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.service.UserMovieService;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.shared.dto.UserMovieDto;
import com.example.moviebookingws.ui.model.request.UserMovieDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("user-movie") //http://localhost:8080/user-movie
public class UserMovieController {

    @Autowired
    UserMovieService userMovieService;

    @Autowired
    MovieScheduleService movieScheduleService;

    @Autowired
    UserService userService;

    @GetMapping("/{userId}/{movieId}")
    public MovieScheduleRest getUserMovieBooked(@PathVariable("userId") String userId, @PathVariable("movieId") String scheduleId) {
        MovieScheduleDto movieScheduleDto = userMovieService.getUserScheduledMovieByMovieId(userId, scheduleId);
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
        movieScheduleRest.setHall(movieScheduleDto.getHall());
        movieScheduleRest.setMovie(movieRest);
        return movieScheduleRest;
    }

    @GetMapping("/rating/{userId}/{movieId}")
    public long getUserMovieRating(@PathVariable("userId") String userId, @PathVariable("movieId") String scheduleId) {
        long rating = userMovieService.getUserMovieRating(userId, scheduleId);
        return rating;
    }

    @GetMapping("/{userId}")
    public Set<MovieScheduleRest> getUserBookedMovies(@PathVariable("userId") String userId) {
        Collection<MovieScheduleDto> movieScheduleDtos = userMovieService.getUserBookedMovies(userId);
        Set<MovieScheduleRest> movieScheduleRests = new HashSet<MovieScheduleRest>();

        for (MovieScheduleDto movieScheduleDto: movieScheduleDtos) {
            MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
            BeanUtils.copyProperties(movieScheduleDto, movieScheduleRest);
            MovieRest movieRest = new MovieRest();
            BeanUtils.copyProperties(movieScheduleDto.getMovie(), movieRest);
            movieRest.setGenreId(movieScheduleDto.getMovie().getGenre().getGenreId());
            ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
            for (ActorEntity actorEntity: movieScheduleDto.getMovie().getPlayedActors()) {
                ActorRest actorRest = new ActorRest();
                BeanUtils.copyProperties(actorEntity,actorRest);
                actors.add(actorRest);
            }
            movieRest.setActors(actors);
            movieScheduleRest.setMovie(movieRest);
            movieScheduleRests.add(movieScheduleRest);
        }
        return movieScheduleRests;
    }

    @PostMapping
    public UserMovieRest scheduleUserMovie(@RequestBody UserMovieDetailsRequestModel userMovieDetailsRequestModel) {
        UserMovieDto userMovieDto = new UserMovieDto();
        MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();
        UserEntity userEntity = new UserEntity();
        UserDto userDto = userService.getUserByUserId(userMovieDetailsRequestModel.getUserId());
        MovieScheduleDto movieScheduleDto = movieScheduleService.getMovieScheduleByScheduleId(userMovieDetailsRequestModel.getMovieId());
        BeanUtils.copyProperties(movieScheduleDto, movieScheduleEntity);
        BeanUtils.copyProperties(userDto, userEntity);
        userMovieDto.setMovie(movieScheduleEntity);
        userMovieDto.setUser(userEntity);
        userMovieDto.setRating(userMovieDetailsRequestModel.getRating());
        UserMovieDto userMovieDto1 = userMovieService.createAttendee(userMovieDto);
        UserMovieRest userMovieRest = new UserMovieRest();
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        UserRest userRest = new UserRest();
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movieScheduleEntity, movieScheduleRest);
        BeanUtils.copyProperties(userEntity, userRest);
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
        userMovieRest.setMovie(movieScheduleRest);
        userMovieRest.setUser(userRest);
        userMovieRest.setRating(userMovieDto1.getRating());
        userMovieRest.setAttendeeId(userMovieDto1.getAttendeeId());
        return userMovieRest;
    }
}
