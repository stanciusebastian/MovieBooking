package com.example.moviebookingws.io.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MoviesGenre")
public class MovieGenreEntity {

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "genre_id")
    private Integer genreId;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}
