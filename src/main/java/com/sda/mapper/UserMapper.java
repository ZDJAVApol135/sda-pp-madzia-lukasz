package com.sda.mapper;

import com.sda.dto.UserDTO;
import com.sda.model.User;

public class UserMapper {

    public UserDTO map(User User){
        return UserDTO.builder()
                .username(User.getUsername())
                .name(User.getName())
                .surname(User.getSurname())
                .age(User.getAge())
                .email(User.getEmail())
                .build();
    }
}