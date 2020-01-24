package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.repositories.MovieScheduleRepository;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.service.RecommenderService;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.ui.model.response.ActorRest;
import com.example.moviebookingws.ui.model.response.RecommenderRest;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("recommender")
public class RecommenderController {
    @Autowired
    RecommenderService recommenderService;

    @Autowired
    UserService userService;

    @Autowired
    MovieScheduleRepository movieScheduleRepository;

    @Autowired
    MovieScheduleService movieScheduleService;
    @GetMapping("/{id}")
    public List<RecommenderRest> getRec(@PathVariable String id){
        UserDto userDto = userService.getUserByUserId(id);
        List<RecommenderRest> movieRests = new ArrayList<RecommenderRest>();
         for(RecommendedItem recommendedItem : recommenderService.getRecommendations(userDto.getId(),3)) {
             RecommenderRest recommenderRest = new RecommenderRest();
             MovieScheduleDto movieScheduleDto =  movieScheduleService.getMovieScheduleById(recommendedItem.getItemID());
             BeanUtils.copyProperties(movieScheduleDto, recommenderRest);
             recommenderRest.setMovieId(movieScheduleDto.getMovie().getMovieId());
             recommenderRest.setName(movieScheduleDto.getMovie().getName());
             recommenderRest.setReleaseDate(movieScheduleDto.getMovie().getReleaseDate());
             recommenderRest.setGenreId(movieScheduleDto.getMovie().getGenre().getGenreId());
             recommenderRest.setRating(recommendedItem.getValue());
             ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
             for (ActorEntity actorEntity: movieScheduleDto.getMovie().getPlayedActors()) {
                 ActorRest actorRest = new ActorRest();
                 BeanUtils.copyProperties(actorEntity,actorRest);
                 actors.add(actorRest);
             }
             recommenderRest.setActors(actors);
            movieRests.add(recommenderRest);
         }
        return movieRests;
    }

}
