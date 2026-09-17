package com.corebank.util;

public class PasswordUtilTest {

    public static void main(String[] args) {

        System.out.println(
                PasswordUtil.hashPassword("alok123"));

        System.out.println(
                PasswordUtil.hashPassword("admin123"));
    }
}