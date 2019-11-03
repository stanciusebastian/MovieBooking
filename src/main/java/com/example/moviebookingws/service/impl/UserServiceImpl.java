package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.UserRepository;
import com.example.moviebookingws.io.entity.UserEntity;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Already exists an account on that email");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }

    public UserDto updateUser(long id, UserDto user) {
        UserEntity userEntity = new UserEntity();

        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("id-" + id);
        }
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setId(id);
        userEntity.setEncryptedPassword(userOptional.get().getEncryptedPassword());
        userEntity.setUserId(userOptional.get().getUserId());

        UserEntity updatedUserDetails = userRepository.save(userEntity);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        List<UserDto> usersDto = new ArrayList<UserDto>();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user: users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            usersDto.add(userDto);
        }
        return usersDto;
    }

    public UserDto getUser(long id) {
        UserDto userDto = new UserDto();
        Optional<UserEntity> user  = userRepository.findById(id);
        if (!user.isPresent())
            throw new EntityNotFoundException("id-" + id);
        BeanUtils.copyProperties(user.get(),userDto);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
