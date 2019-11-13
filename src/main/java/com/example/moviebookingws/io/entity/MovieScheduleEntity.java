package com.example.moviebookingws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class MovieScheduleEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable =  false, length = 35)
    private String hall;

    @OneToMany(mappedBy = "movie")
    Set<MovieEntity> movies;

    @Temporal(TemporalType.DATE)
    private Date schedule;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
}
