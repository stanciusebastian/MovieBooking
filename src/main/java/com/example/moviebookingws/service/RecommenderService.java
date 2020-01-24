package com.example.moviebookingws.service;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

public interface RecommenderService {
    public List<RecommendedItem> getRecommendations(long userID, int noOfRecommendations);
}