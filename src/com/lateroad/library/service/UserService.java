package com.lateroad.library.service;

import com.lateroad.library.database.dao.UserDAO;
import com.lateroad.library.entity.User;
import com.lateroad.library.exception.ItemNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class UserService {
    private static UserService instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static UserService getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new UserService();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    private UserDAO userDAO;

    private UserService(){
         userDAO = UserDAO.getInstance();
    }

    public User userGetByEmailAndPassword(String login, String password) throws IOException, ItemNotFoundException {
        User user = null;
        if ((user = userDAO.find(new User(login, password))) != null){
            return user;
        }
        throw new ItemNotFoundException();
    }

    public List<User> getUserList() throws ItemNotFoundException {
        List<User> users = null;
        if ((users = userDAO.findAll()) != null){
            return users;
        }
        throw new ItemNotFoundException();
    }

    public void delete(String userID) {
        userDAO.delete(userID);
    }

    public User createUser(String name, String surname, String login, String password) {
        User user = new User(login, password, name, surname);
        userDAO.insert(user);
        return userDAO.find(new User(login, password));
    }
}