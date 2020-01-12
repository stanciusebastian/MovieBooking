package com.example.moviebookingws.ui.controller;

import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.ui.model.request.MovieDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.MovieRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies") //http://localhost::8080/movies
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{movieId}")
    public MovieRest getMovie(@PathVariable String movieId) {
        MovieDto movieDto = movieService.getMovieByMovieId(movieId);
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movieDto, movieRest);
        return movieRest;
    }

    @PostMapping
    public MovieRest createMovie(@RequestBody MovieDetailsRequestModel movieDetailsRequestModel){
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movieDetailsRequestModel,movieDto);
        MovieDto movie = movieService.createMovie(movieDto);
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movie,movieRest);
        return movieRest;
    }

    @PutMapping("/{movieId}")
    public MovieRest updateMovie(@PathVariable String movieId, @RequestBody MovieDetailsRequestModel movieDetailsRequestModel){
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movieDetailsRequestModel, movieDto);
        MovieDto movie  = movieService.updateMovie(movieId, movieDto);
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movie, movieRest);
        return movieRest;
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable String movieId){
        movieService.deleteMovie(movieId);
    }
}
