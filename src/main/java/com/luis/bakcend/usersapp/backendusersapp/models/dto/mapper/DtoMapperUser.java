package com.luis.bakcend.usersapp.backendusersapp.models.dto.mapper;

import com.luis.bakcend.usersapp.backendusersapp.models.dto.UserDto;
import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;

public class DtoMapperUser {

    private User user;

    private DtoMapperUser() {

    }

    public static DtoMapperUser builder() {
        return new DtoMapperUser();

    }

    public DtoMapperUser serUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build() {

        if (user == null) {
            throw new RuntimeException("Debe pasar la entity user");
        }
        return new UserDto(this.user.getId(), user.getUsername(), user.getEmail());

    }

}
