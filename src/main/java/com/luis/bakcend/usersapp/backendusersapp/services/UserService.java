package com.luis.bakcend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import com.luis.bakcend.usersapp.backendusersapp.models.dto.UserDto;
import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;

import jakarta.validation.Valid;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    Optional<User> findById2(Long id);

    UserDto save(@Valid User user);

    void remove(Long id);
}
