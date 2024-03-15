package com.luis.bakcend.usersapp.backendusersapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luis.bakcend.usersapp.backendusersapp.models.dto.UserDto;
import com.luis.bakcend.usersapp.backendusersapp.models.dto.mapper.DtoMapperUser;
import com.luis.bakcend.usersapp.backendusersapp.models.entities.Role;
import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;
import com.luis.bakcend.usersapp.backendusersapp.repositories.RoleRepository;
import com.luis.bakcend.usersapp.backendusersapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {

        List<User> users = (List<User>) repository.findAll();
        return users
                .stream()
                .map(u -> DtoMapperUser.builder().serUser(u).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {

        return repository.findById(id).map(u -> DtoMapperUser
                .builder()
                .serUser(u)
                .build());
    }

    @Override
    @Transactional
    public UserDto save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();

        if (o.isPresent()) {
            roles.add(o.orElseThrow());
        }

        user.setRoles(roles);

        return DtoMapperUser.builder().serUser(repository.save(user)).build();

    }

    @Override
    @Transactional
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public Optional<User> findById2(Long id) {

        return repository.findById(id);

    }

}
