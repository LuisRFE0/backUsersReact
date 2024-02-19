package com.luis.bakcend.usersapp.backendusersapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
