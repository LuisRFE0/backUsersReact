package com.luis.bakcend.usersapp.backendusersapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.luis.bakcend.usersapp.backendusersapp.models.dto.UserDto;
import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;
import com.luis.bakcend.usersapp.backendusersapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class Usercontroller {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {

        Optional<UserDto> userOptional = service.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validation(result);
        }

        Optional<User> o = service.findById2(id);

        if (o.isPresent()) {
            User userDb = o.orElseThrow();

            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<UserDto> o = service.findById(id);

        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        // Generar un tipo ongeto para almacenar las validaciones de los errores
        // generados
        Map<String, String> errors = new HashMap<>();

        // iterar sobre cada error para agregarlo al map

        result.getFieldErrors().forEach((err) -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        // devolver el json con los campos y errores de cada uno
        return ResponseEntity.badRequest().body(errors);

    }

}
