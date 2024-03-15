package com.luis.bakcend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("Select u from User u where u.username = ?1 ")
    Optional<User> getUserByUsername(String username);

}
