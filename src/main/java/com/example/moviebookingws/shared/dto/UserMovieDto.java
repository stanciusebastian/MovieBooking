package com.example.moviebookingws.shared.dto;

import com.example.moviebookingws.io.entity.MovieEntity;
import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.entity.UserEntity;

public class UserMovieDto {
    private long Id;
    private String attendeeId;
    private Integer rating;
    private MovieScheduleEntity movie;
    private UserEntity user;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        this.attendeeId = attendeeId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public MovieScheduleEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieScheduleEntity movie) {
        this.movie = movie;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
