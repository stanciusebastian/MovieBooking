package com.example.moviebookingws.io.entity;


import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Movies")
public class MovieEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable = false, length = 35)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private MovieScheduleEntity schedule;

    @ManyToMany
    @JoinTable(name = "user_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> usersJoined;

    @ManyToMany
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<MovieEntity> actors;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<GenreEntity> genres;

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public MovieScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(MovieScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public Set<UserEntity> getUsersJoined() {
        return usersJoined;
    }

    public void setUsersJoined(Set<UserEntity> usersJoined) {
        this.usersJoined = usersJoined;
    }

    public Set<MovieEntity> getActors() {
        return actors;
    }

    public void setActors(Set<MovieEntity> actors) {
        this.actors = actors;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(nullable = false)
    private Integer rating;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
