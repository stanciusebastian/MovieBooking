package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.io.entity.UserEntity;
import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.service.UserMovieService;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.shared.dto.UserMovieDto;
import com.example.moviebookingws.ui.model.request.UserMovieDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.MovieScheduleRest;
import com.example.moviebookingws.ui.model.response.UserMovieRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("user-movie") //http://localhost:8080/user-movie
public class UserMovieController {

    @Autowired
    UserMovieService userMovieService;

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @GetMapping("/{userId}/{movieId}")
    public MovieScheduleRest getUserMovieBooked(@PathVariable("userId") String userId, @PathVariable("movieId") String scheduleId) {
        MovieScheduleDto movieScheduleDtos = userMovieService.getUserScheduledMovieByMovieId(userId, scheduleId);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(movieScheduleDtos, movieScheduleRest);
        movieScheduleRest.setHall(movieScheduleDtos.getHall());
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
            movieScheduleRest.setMovieId(movieScheduleDto.getMovie().getMovieId());
            movieScheduleRests.add(movieScheduleRest);
        }
        return movieScheduleRests;
    }

    @PostMapping
    public UserMovieRest scheduleUserMovie(@RequestBody UserMovieDetailsRequestModel userMovieDetailsRequestModel) {
        UserMovieDto userMovieDto = new UserMovieDto();
        MovieEntity movieEntity = new MovieEntity();
        UserEntity userEntity = new UserEntity();
        UserDto userDto = userService.getUserByUserId(userMovieDetailsRequestModel.getUserId());
        MovieDto movieDto = movieService.getMovieByMovieId(userMovieDetailsRequestModel.getMovieId());
        BeanUtils.copyProperties(movieDto, movieEntity);
        BeanUtils.copyProperties(userDto, userEntity);
        userMovieDto.setMovie(movieEntity);
        userMovieDto.setUser(userEntity);
        userMovieDto.setRating(0);
        UserMovieDto userDto1 = userMovieService.createAttendee(userMovieDto);
        UserMovieRest userMovieRest = new UserMovieRest();
        userMovieRest.setMovieId(userDto1.getMovie().getMovieId());
        userMovieRest.setUserId(userDto1.getUser().getUserId());
        return userMovieRest;
    }
}
