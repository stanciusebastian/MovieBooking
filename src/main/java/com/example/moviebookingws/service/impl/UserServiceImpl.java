package com.example.moviebookingws.service.impl;

import com.example.moviebookingws.io.repositories.UserRepository;
import com.example.moviebookingws.io.entity.UserEntity;
import com.example.moviebookingws.service.UserService;
import com.example.moviebookingws.shared.dto.UserDto;
import com.example.moviebookingws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
    private UserRepository userRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserEntity userOptional = userRepository.findByUserId(userId);
        if (userOptional==null) {
            throw new EntityNotFoundException("UserId-" + userId);
        }

        //userOptional.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userOptional.setFirstName(user.getFirstName());
        userOptional.setLastName(user.getLastName());
        userOptional.setEmail(user.getEmail());

        UserEntity updatedUserDetails = userRepository.save(userOptional);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(updatedUserDetails, userDto);
        return userDto;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity user  = userRepository.findByUserId(userId);
        if (user==null)
            throw new EntityNotFoundException("id-" + userId);
        userRepository.delete(user);
    }

    @Override
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

    @Override
    public UserDto getUserByUserId(String id) {
        UserDto userDto = new UserDto();
        Optional<UserEntity> user  = Optional.ofNullable(userRepository.findByUserId(id));
        if (!user.isPresent())
            throw new EntityNotFoundException("id-" + id);
        BeanUtils.copyProperties(user.get(),userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(String email){
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity==null) throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
    }
}
