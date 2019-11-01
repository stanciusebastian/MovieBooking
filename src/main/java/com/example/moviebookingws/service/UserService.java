package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    boolean deleteUser(long Id);

    UserDto updateUser(long Id, UserDto user);

    List<UserDto> getUsers();

    UserDto getUserById(long Id);
}
