package com.luis.bakcend.usersapp.backendusersapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> findAll() {

        return (List<User>) repository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {

        return repository.findById(id);

    }

    @Override
    @Transactional
    public User save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();

        if (o.isPresent()) {
            roles.add(o.orElseThrow());
        }

        user.setRoles(roles);

        return repository.save(user);

    }

    @Override
    @Transactional
    public void remove(Long id) {

        repository.deleteById(id);

    }

}
