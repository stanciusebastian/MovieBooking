package com.example.moviebookingws.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserMovie")
public class UserMovieEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @Column(nullable = true, length = 10)
    private long rating;

    public UserMovieEntity(UserEntity user, MovieEntity movie) {
        this.user = user;
        this.movie = movie;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }
}
