package com.corebank.ui;

import com.corebank.model.User;
import com.corebank.service.AuthService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();

        System.out.println("================================");
        System.out.println("       CORE BANK SYSTEM");
        System.out.println("================================");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authService.login(username, password);

        if (user == null) {
            System.out.println("Login failed!");
            scanner.close();
            return;
        }

        System.out.println();
        System.out.println("Login successful!");
        System.out.println("Welcome, " + user.getUsername());
        System.out.println("Role: " + user.getRole());

        if ("CUSTOMER".equals(user.getRole())) {
            CustomerMenu.show(scanner, user);
        } else if ("ADMIN".equals(user.getRole())) {
            AdminMenu.show(scanner, user);
        }

        scanner.close();
    }
}