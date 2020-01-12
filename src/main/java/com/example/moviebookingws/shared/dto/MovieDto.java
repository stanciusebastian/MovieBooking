package com.example.moviebookingws.shared.dto;

import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.entity.UserMovieEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class MovieDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long Id;
    private String movieId;
    private long genreId;
    private String name;
    private Set<UserMovieEntity> joinedUsers;
    private Set<ActorEntity> playedActors;
    private Date releaseDate;
    private Date createdAt;
    private Date modifiedAt;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public Set<UserMovieEntity> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(Set<UserMovieEntity> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    public Set<ActorEntity> getPlayedActors() {
        return playedActors;
    }

    public void setPlayedActors(Set<ActorEntity> playedActors) {
        this.playedActors = playedActors;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
