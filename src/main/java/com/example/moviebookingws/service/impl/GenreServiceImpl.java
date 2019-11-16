package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.GenreEntity;
import com.example.moviebookingws.io.repositories.GenreRepository;
import com.example.moviebookingws.service.GenreService;
import com.example.moviebookingws.shared.dto.GenreDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private Utils utils;

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        GenreEntity genreEntity = new GenreEntity();
        BeanUtils.copyProperties(genreDto, genreEntity);
        genreEntity.setGenreId(utils.generateGenreId(30));
        GenreEntity genre = genreRepository.save(genreEntity);
        GenreDto genreDetails = new GenreDto();
        BeanUtils.copyProperties(genre, genreDetails);

        return genreDetails;
    }
}
