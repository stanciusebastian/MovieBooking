package com.example.moviebookingws.ui.controller;

import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.entity.GenreEntity;
import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.service.ActorService;
import com.example.moviebookingws.service.GenreService;
import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.shared.dto.ActorDto;
import com.example.moviebookingws.shared.dto.GenreDto;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.ui.model.request.MovieDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.MovieRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("movies") //http://localhost::8080/movies
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    GenreService genreService;

    @Autowired
    ActorService actorService;

    @GetMapping("/{movieId}")
    public MovieRest getMovie(@PathVariable String movieId) {
        MovieDto movieDto = movieService.getMovieByMovieId(movieId);
        MovieRest movieRest = new MovieRest();
        movieRest.setGenreId(movieDto.getGenre().getGenreId());
        ArrayList<String> actorsIds = new ArrayList<String>();
        for (ActorEntity actorEntity: movieDto.getPlayedActors()) {
            actorsIds.add(actorEntity.getActorId());
        }
        movieRest.setActorsIds(actorsIds);
        BeanUtils.copyProperties(movieDto, movieRest);
        return movieRest;
    }

    @PostMapping
    public MovieRest createMovie(@RequestBody MovieDetailsRequestModel movieDetailsRequestModel){
        MovieDto movieDto = new MovieDto();
        GenreDto genre = genreService.getGenreByGenreId(movieDetailsRequestModel.getGenreId());
        GenreEntity genreEntity = new GenreEntity();
        BeanUtils.copyProperties(genre,genreEntity);
        Set<ActorDto> actorDtoSet = actorService.getActorsByActorsIds(movieDetailsRequestModel.getActorsIds());
        Set<ActorEntity> actorEntities = new HashSet<ActorEntity>();
        for (ActorDto actorDto: actorDtoSet) {
            ActorEntity actorEntity = new ActorEntity();
            BeanUtils.copyProperties(actorDto,actorEntity);
            actorEntities.add(actorEntity);
        }

        movieDto.setPlayedActors(actorEntities);
        movieDto.setGenre(genreEntity);
        movieDto.setName(movieDetailsRequestModel.getName());
        movieDto.setReleaseDate(movieDetailsRequestModel.getReleaseDate());
        movieDto.setCreatedAt(new Date());
        movieDto.setModifiedAt(new Date());
        MovieDto movie = movieService.createMovie(movieDto);
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movie,movieRest);
        ArrayList<String> actorsIds = new ArrayList<String>();
        for (ActorEntity actorEntity: movie.getPlayedActors()) {
            actorsIds.add(actorEntity.getActorId());
        }
        movieRest.setActorsIds(actorsIds);
        return movieRest;
    }

    @PutMapping("/{movieId}")
    public MovieRest updateMovie(@PathVariable String movieId, @RequestBody MovieDetailsRequestModel movieDetailsRequestModel){
        MovieDto movieDto = new MovieDto();
        GenreDto genre = genreService.getGenreByGenreId(movieDetailsRequestModel.getGenreId());
        GenreEntity genreEntity = new GenreEntity();
        BeanUtils.copyProperties(genre,genreEntity);
        BeanUtils.copyProperties(movieDetailsRequestModel, movieDto);
        movieDto.setGenre(genreEntity);
        movieDto.setModifiedAt(new Date());
        movieDto.setName(movieDetailsRequestModel.getName());
        MovieDto movie  = movieService.updateMovie(movieId, movieDto);
        MovieRest movieRest = new MovieRest();
        BeanUtils.copyProperties(movie, movieRest);
        movieRest.setGenreId(movie.getGenre().getGenreId());
        ArrayList<String> actorsIds = new ArrayList<String>();
        for (ActorEntity actorEntity: movie.getPlayedActors()) {
            actorsIds.add(actorEntity.getActorId());
        }
        movieRest.setActorsIds(actorsIds);
        return movieRest;
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable String movieId){
        movieService.deleteMovie(movieId);
    }
}
