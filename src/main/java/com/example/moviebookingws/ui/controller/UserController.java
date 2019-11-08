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

    /*@GetMapping("/all")
    public List<UserRest> getUsers(){
        List<UserDto> users = userService.getUsers();
        List<UserRest> usersRest = new ArrayList<UserRest>();
        for (UserDto userDto: users) {
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(userDto,userRest);
            usersRest.add(userRest);
        }
        return usersRest;
    }*/


    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id){
        UserDto userDto = userService.getUserByUserId(id);
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(userDto, userRest);
        return userRest;
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

    @PutMapping("/{id}")
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails){
        UserDto userDto  = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto user  = userService.updateUser(id, userDto);
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(user, userRest);
        return userRest;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

}
