package com.example.moviebookingws.ui.model.request;

import java.util.Date;

public class MovieDetailsRequestModel {
    private String name;
    private String genreId;
    private String[] actorsIds;
    private Date releaseDate;

    public String[] getActorsIds() {
        return actorsIds;
    }

    public void setActorsIds(String[] actorsIds) {
        this.actorsIds = actorsIds;
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
}
