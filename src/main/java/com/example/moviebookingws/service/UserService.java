package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);

    void deleteUser(long Id);

    UserDto updateUser(long Id, UserDto user);

    List<UserDto> getUsers();

    UserDto getUser(long Id);

}
