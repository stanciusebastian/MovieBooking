package com.example.moviebookingws.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class MovieScheduleDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long Id;
    private long scheduleId;
    private long movieId;
    private String hall;
    private Date schedule;
    private Date createdAt;
    private Date modifiedAt;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

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
