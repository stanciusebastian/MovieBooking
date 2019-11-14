package com.example.moviebookingws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MovieSchedule")
public class MovieScheduleEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable =  false, length = 35)
    private String hall;

    @ManyToOne
    private MovieEntity movie;

    @Column(nullable = true)
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


    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
}
