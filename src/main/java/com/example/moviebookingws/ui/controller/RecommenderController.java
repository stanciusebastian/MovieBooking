package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.service.RecommenderService;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.UserDto;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("recommender")
public class RecommenderController {
    @Autowired
    RecommenderService recommenderService;
    @Autowired
    UserService userService;

    @PostMapping
    public String alo(){
        return "wtf";
    }

    @GetMapping("/{id}")
    public List<RecommendedItem> getRec(@PathVariable String id){
        System.out.println("alabala");
        UserDto userDto = userService.getUserByUserId(id);
        return recommenderService.getRecommendations(userDto.getId(),3);
    }

}
