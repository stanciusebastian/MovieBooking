package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.entity.GenreEntity;
import com.example.moviebookingws.io.repositories.GenreRepository;
import com.example.moviebookingws.service.GenreService;
import com.example.moviebookingws.shared.dto.GenreDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
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

    @Override
    public GenreDto getGenreByGenreId(String genreId) {
        GenreDto genreDto = new GenreDto();
        Optional<GenreEntity> genreEntity  = Optional.ofNullable(genreRepository.findByGenreId(genreId));
        if (!genreEntity.isPresent())
            throw new EntityNotFoundException("id-" + genreId);
        BeanUtils.copyProperties(genreEntity.get(),genreDto);
        return genreDto;
    }
}
