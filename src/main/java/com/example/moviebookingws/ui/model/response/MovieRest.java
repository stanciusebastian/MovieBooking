package com.example.moviebookingws.ui.model.response;

import java.util.Date;

public class MovieRest {
    private String movieId;
    private String name;
    private String genreId;
    private Date releaseDate;
    private String[] actorsIds;
    private Date createdAt;
    private Date modifiedAt;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getActorsIds() {
        return actorsIds;
    }

    public void setActorsIds(String[] actorsIds) {
        this.actorsIds = actorsIds;
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
