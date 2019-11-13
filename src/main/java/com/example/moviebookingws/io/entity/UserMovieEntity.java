package com.example.moviebookingws.io.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class UserMovieEntity implements Serializable {

    @Column(nullable = false, length = 10)
    private Integer userId;

    @Column(nullable = false, length = 10)
    private Integer movieId;

    @Column(nullable = true, length = 10)
    private Integer rating;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
