package com.example.moviebookingws.ui.controller;

import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.ui.model.request.UserDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") //http://localhost::8080/users

public class UserController {
    @Autowired
    UserService userService;


    @GetMapping
    public String getUser(){
        return "get user";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(userDetails,dto);

        UserDto returnDto = userService.createUser(dto);
        UserRest returnUser = new UserRest();
        BeanUtils.copyProperties(returnDto,returnUser);

        return returnUser;


    }

    @PutMapping
    public String updateUser(){
        return "update user";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user";
    }

}
