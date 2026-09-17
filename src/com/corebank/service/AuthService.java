package com.corebank.service;

import com.corebank.dao.UserDAO;
import com.corebank.model.User;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        userDAO = new UserDAO();
    }

    public User login(String username, String password) {

        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty()) {
            return null;
        }

        return userDAO.login(username, password);
    }
}