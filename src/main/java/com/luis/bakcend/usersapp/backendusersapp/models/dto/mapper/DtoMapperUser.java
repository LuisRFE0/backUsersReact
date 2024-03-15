package com.luis.bakcend.usersapp.backendusersapp.models.dto.mapper;

import com.luis.bakcend.usersapp.backendusersapp.models.dto.UserDto;
import com.luis.bakcend.usersapp.backendusersapp.models.entities.User;

public class DtoMapperUser {

    private static DtoMapperUser mapper;

    private User user;

    private DtoMapperUser() {

    }

    public static DtoMapperUser getInstance() {
        mapper = new DtoMapperUser();
        return mapper;
    }

    public DtoMapperUser serUser(User user) {
        this.user = user;
        return mapper;
    }

    public UserDto build() {

        if (user == null) {
            throw new RuntimeException("Debe pasar la entity user");
        }
        return new UserDto(this.user.getId(), user.getUsername(), user.getEmail());

    }

}
