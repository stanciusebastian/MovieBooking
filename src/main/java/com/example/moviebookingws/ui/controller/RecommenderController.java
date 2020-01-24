package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.io.entity.ActorEntity;
import com.example.moviebookingws.io.repositories.MovieScheduleRepository;
import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.service.RecommenderService;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.ui.model.response.ActorRest;
import com.example.moviebookingws.ui.model.response.MovieRest;
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
    public List<MovieRest> getRec(@PathVariable String id){
        UserDto userDto = userService.getUserByUserId(id);
        List<MovieRest> movieRests = new ArrayList<MovieRest>();
         for(RecommendedItem recommendedItem : recommenderService.getRecommendations(userDto.getId(),3)) {
             MovieRest movieRest = new MovieRest();
             MovieScheduleDto movieScheduleDto =  movieScheduleService.getMovieScheduleById(recommendedItem.getItemID());
             BeanUtils.copyProperties(movieScheduleDto, movieRest);
             movieRest.setMovieId(movieScheduleDto.getMovie().getMovieId());
             movieRest.setName(movieScheduleDto.getMovie().getName());
             movieRest.setReleaseDate(movieScheduleDto.getMovie().getReleaseDate());
             movieRest.setGenreId(movieScheduleDto.getMovie().getGenre().getGenreId());
             ArrayList<ActorRest> actors = new ArrayList<ActorRest>();
             for (ActorEntity actorEntity: movieScheduleDto.getMovie().getPlayedActors()) {
                 ActorRest actorRest = new ActorRest();
                 BeanUtils.copyProperties(actorEntity,actorRest);
                 actors.add(actorRest);
             }
             movieRest.setActors(actors);
            movieRests.add(movieRest);
         }
        return movieRests;
    }

}
