package com.corebank.dao;

import com.corebank.model.Account;

public class AccountDAOTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account account = accountDAO.getAccount(1001001);

        if (account != null) {
            System.out.println("Account found!");
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Status: " + account.getStatus());
        } else {
            System.out.println("Account not found!");
        }
    }
}