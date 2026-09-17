package com.corebank.service;

import com.corebank.model.User;

public class AuthServiceTest {

    public static void main(String[] args) {

        AuthService authService = new AuthService();

        User user = authService.login("admin", "admin123");

        if (user != null) {
            System.out.println("Authentication successful!");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("Authentication failed!");
        }
    }
}