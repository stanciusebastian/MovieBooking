package com.example.moviebookingws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MovieSchedule")
public class MovieScheduleEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @Column
    private String scheduleId;

    @Column(nullable =  false, length = 35)
    private String hall;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movie;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date schedule;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
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
}
