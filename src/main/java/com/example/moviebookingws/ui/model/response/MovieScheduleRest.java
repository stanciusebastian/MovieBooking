package com.example.moviebookingws.ui.model.response;

import java.util.Date;

public class MovieScheduleRest {
    private String hall;
    private Date schedule;
    private String scheduleId;
    private MovieRest movie;
    private Date createdAt;
    private Date modifiedAt;

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

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public MovieRest getMovie() {
        return movie;
    }

    public void setMovie(MovieRest movie) {
        this.movie = movie;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
