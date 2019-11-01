package com.example.moviebookingws.ui.controller;

import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.ui.model.request.UserDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users") //http://localhost::8080/users

public class UserController {
    @Autowired
    UserService userService;

    public List<UserRest> getUsers(){
        List<UserDto> users = userService.getUsers();
        List<UserRest> usersRest = new ArrayList<UserRest>();
        BeanUtils.copyProperties(users,usersRest);
        return usersRest;
    }

    @GetMapping("/{userId}")
    public String getUser(@RequestParam Integer userId){
        return "get user";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);

        UserDto user = userService.createUser(userDto);
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(user,userRest);

        return userRest;


    }

    @PutMapping("/{userId}")
    public String updateUser(){
        return "update user";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(){
        return "delete user";
    }

}
