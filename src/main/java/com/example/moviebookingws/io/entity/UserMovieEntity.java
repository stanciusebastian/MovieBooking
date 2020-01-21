package com.example.moviebookingws.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserMovie")
public class UserMovieEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long Id;

    @Column
    private String attendeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    private MovieScheduleEntity movieSchedule;

    @Column(nullable = true, length = 10)
    private Integer rating;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MovieScheduleEntity getMovieSchedule() {
        return movieSchedule;
    }

    public void setMovieSchedule(MovieScheduleEntity movieSchedule) {
        this.movieSchedule = movieSchedule;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
