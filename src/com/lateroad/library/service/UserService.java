package com.lateroad.library.service;

import com.lateroad.library.database.dao.UserDAO;
import com.lateroad.library.entity.User;
import com.lateroad.library.exception.UserNotFoundException;

import java.io.*;

public class UserService {
    UserDAO userDAO = UserDAO.getInstance();

    public User userGetByEmailAndPassword(String login, String password) throws IOException, UserNotFoundException {
        User user = null;
        if ((user = userDAO.find(new User(login, password))) != null){
            return user;
        }
    throw new UserNotFoundException();
    }
}