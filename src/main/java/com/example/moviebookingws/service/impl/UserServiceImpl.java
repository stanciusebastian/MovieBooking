package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.UserRepository;
import com.example.moviebookingws.io.entity.UserEntity;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userEntity.setEncryptedPassword("test1");
        userEntity.setUserId("testUserId");
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }

    public UserDto updateUser(long Id, UserDto user) {

        return user;
    }

    public boolean deleteUser(long Id) {

        return true;
    }

    public List<UserDto> getUsers() {

        List<UserDto> users = new ArrayList<UserDto>();
        return users;
    }

    public UserDto getUserById(long Id) {

        UserDto user = new UserDto();
        return user;
    }
}
