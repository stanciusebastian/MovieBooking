package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.recommender.CollaborativeFiltering;
import com.example.moviebookingws.service.RecommenderService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommenderServiceImpl implements RecommenderService {
    @Autowired
    private CollaborativeFiltering collaborativeFiltering;


    @Override
    public List<RecommendedItem> getRecommendations(long userID, int noOfRecommendations) {
        try {
            return collaborativeFiltering.getRecommendations(userID,noOfRecommendations);
        } catch (TasteException e) {
            return null;
        }
    }
}
