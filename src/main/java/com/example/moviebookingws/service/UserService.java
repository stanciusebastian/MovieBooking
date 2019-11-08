package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);

    UserDto getUser(String email);

    void deleteUser(String userId);

    UserDto updateUser(String Id, UserDto user);

    List<UserDto> getUsers();

    UserDto getUserByUserId(String userId);

}
