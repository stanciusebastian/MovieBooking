package com.example.moviebookingws.ui.model.response;

public class UserMovieRest {
    private String attendeeId;
    private MovieScheduleRest movie;
    private UserRest user;
    private Integer rating;

    public String getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        this.attendeeId = attendeeId;
    }

    public MovieScheduleRest getMovie() {
        return movie;
    }

    public void setMovie(MovieScheduleRest movie) {
        this.movie = movie;
    }

    public UserRest getUser() {
        return user;
    }

    public void setUser(UserRest user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
