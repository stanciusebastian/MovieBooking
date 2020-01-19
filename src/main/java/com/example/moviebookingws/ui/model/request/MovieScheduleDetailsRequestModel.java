package com.example.moviebookingws.ui.model.request;

import java.util.Date;

public class MovieScheduleDetailsRequestModel {
    private String hall;
    private Date schedule;
    private String movieId;

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
