package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.GenreEntity;
import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.io.repositories.GenreRepository;
import com.example.moviebookingws.io.repositories.MovieRepository;
import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private Utils utils;

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        MovieEntity movieEntity = new MovieEntity();
        BeanUtils.copyProperties(movieDto, movieEntity);
        movieEntity.setMovieId(utils.generateMovieId(30));
        MovieEntity movie = movieRepository.save(movieEntity);
        MovieDto movieDetails = new MovieDto();
        BeanUtils.copyProperties(movie, movieDetails);

        return movieDetails;
    }

    @Override
    public MovieDto getMovieByMovieId(String movieId) {
        MovieDto movieDto = new MovieDto();
        Optional<MovieEntity> movie  = Optional.ofNullable(movieRepository.findByMovieId(movieId));
        if (!movie.isPresent())
            throw new EntityNotFoundException("id-" + movieId);
        BeanUtils.copyProperties(movie.get(),movieDto);
        return movieDto;
    }

    @Override
    public MovieDto updateMovie(String movieId, MovieDto movieDto) {
        MovieEntity movie = movieRepository.findByMovieId(movieId);
        if (movie==null) {
            throw new EntityNotFoundException("MovieId-" + movieId);
        }
        movie.setName(movieDto.getName());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setGenre(movieDto.getGenre());
        movie.setModifiedAt(movieDto.getModifiedAt());
        MovieEntity movieEntity = movieRepository.save(movie);
        MovieDto movieDto1 = new MovieDto();
        BeanUtils.copyProperties(movieEntity, movieDto1);
        return movieDto1;
    }

    @Override
    public void deleteMovie(String movieId) {
        MovieEntity movieEntity = movieRepository.findByMovieId(movieId);
        if (movieEntity==null)
            throw new EntityNotFoundException("id-" + movieId);
        movieRepository.delete(movieEntity);
    }
}
