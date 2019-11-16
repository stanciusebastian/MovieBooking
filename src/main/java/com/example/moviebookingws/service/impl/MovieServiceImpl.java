package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.io.repositories.MovieRepository;
import com.example.moviebookingws.service.MovieService;
import com.example.moviebookingws.shared.dto.MovieDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

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
}
