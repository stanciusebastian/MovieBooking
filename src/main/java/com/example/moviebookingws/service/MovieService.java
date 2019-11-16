package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.MovieDto;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
}
