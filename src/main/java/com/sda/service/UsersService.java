package com.sda.service;

import com.sda.dao.UsersDAO;
import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.mapper.UserMapper;
import com.sda.model.User;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

public class UsersService {

    private final UsersDAO usersDAO;
    private final UserMapper usersMapper;

    public List<UserDTO> findAll() {
        // List<User> users = usersDAO.findAll();
        // List<UserDTO> userDTOS = new ArrayList<>();

        //for (User user : users){
        //     UserDTO dto = usersMapper.map(user);
        //     userDTOS.add(dto);
        // }
        // return userDTOS;

        return usersDAO.findAll().stream()
                .map(user -> usersMapper.map(user))
                .toList();

    }

    public UserDTO findByUsername(String username) {
        User user = usersDAO.findByUsername(username);

        if (user == null) {
            String message = "User with username: '%s' not found".formatted(username);
            throw new NotFoundException(message);
        }

        UserDTO userDTO = usersMapper.map(user);
        return userDTO;
    }

    public void create(User user) {
        boolean exists = usersDAO.exists(user.getUsername());
        if (exists) {
            String message = "User: '%s' already exists".formatted(user);
            throw new UsernameConflictException(message);
        }
        usersDAO.create(user);
    }

    public UserDTO update(User user, String username) {
        if (user.getUsername().equals(username)) {
            throw new UsernameConflictException("Usernames dose not match!");
        }
        boolean exists = usersDAO.exists(username);
        if (!exists) {
            String message = "User with username: '%s' not found".formatted(username);
            throw new NotFoundException(message);
        }
        User updatedUser = usersDAO.update(user);
        UserDTO userDTO = usersMapper.map(updatedUser);
        return userDTO;
    }
}