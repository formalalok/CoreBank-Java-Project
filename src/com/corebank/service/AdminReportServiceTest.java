package com.corebank.service;

import com.corebank.model.Account;

import java.util.List;

public class AdminReportServiceTest {

    public static void main(String[] args) {

        AdminReportService service =
                new AdminReportService();

        List<Account> accounts =
                service.getAllAccounts();

        System.out.println("========== BANK ACCOUNT REPORT ==========");

        for (Account account : accounts) {

            System.out.println(
                    "Account: " +
                    account.getAccountNumber());

            System.out.println(
                    "Type: " +
                    account.getAccountType());

            System.out.println(
                    "Balance: ₹" +
                    account.getBalance());

            System.out.println(
                    "Status: " +
                    account.getStatus());

            System.out.println("-----------------------------------------");
        }

        System.out.println(
                "Total Accounts: " +
                service.getTotalAccounts());

        System.out.println(
                "Active Accounts: " +
                service.getActiveAccounts());

        System.out.println(
                "Frozen Accounts: " +
                service.getFrozenAccounts());
    }
}