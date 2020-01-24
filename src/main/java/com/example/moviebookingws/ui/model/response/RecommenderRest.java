package com.example.moviebookingws.ui.model.response;

public class RecommenderRest extends MovieRest{
    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float value) {
        this.rating = value;
    }
}
