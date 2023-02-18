package com.sda;

import com.sda.controller.UsersController;
import com.sda.dao.UsersDAO;
import com.sda.db.HibernateUtils;
import com.sda.exception.NotFoundException;
import com.sda.model.User;
import com.sda.service.UsersService;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        UsersDAO usersDAO = new UsersDAO();

        User user = new User();
        user.setUsername("root");

        usersDAO.create(user);
            }
}
