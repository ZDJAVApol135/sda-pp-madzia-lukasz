package com.sda.service;

import com.sda.dao.UsersDAO;
import com.sda.dto.UserDTO;
import com.sda.mapper.UserMapper;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

public class UsersService {

    private final UsersDAO usersDAO;
    private final UserMapper usersMapper;

    public List<UserDTO> findAll(){
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
}
