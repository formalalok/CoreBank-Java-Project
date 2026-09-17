package com.corebank.ui;

import com.corebank.exception.AccountNotFoundException;
import com.corebank.model.Account;
import com.corebank.model.User;
import com.corebank.service.AccountService;
import com.corebank.service.AdminReportService;
import com.corebank.util.InputUtil;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public static void show(Scanner scanner, User user) {

        AccountService accountService = new AccountService();
        AdminReportService reportService =
                new AdminReportService();

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("============ ADMIN MENU ============");
            System.out.println("1. Freeze Account");
            System.out.println("2. Unfreeze Account");
            System.out.println("3. Account Report");
            System.out.println("4. Bank Summary Report");
            System.out.println("5. Logout");
            System.out.println("====================================");

            System.out.print("Enter choice: ");

            try {

                int choice = InputUtil.readInt(scanner);

                switch (choice) {

                    case 1:

                        System.out.print(
                                "Enter account number: ");

                        long freezeAccount = InputUtil.readLong(scanner);

                        accountService.freezeAccount(
                                freezeAccount);

                        System.out.println(
                                "Account frozen successfully!");
                        break;

                    case 2:

                        System.out.print(
                                "Enter account number: ");

                        long unfreezeAccount = InputUtil.readLong(scanner);

                        accountService.unfreezeAccount(
                                unfreezeAccount);

                        System.out.println(
                                "Account unfrozen successfully!");
                        break;

                    case 3:

                        System.out.print(
                                "Enter account number: ");

                        long accountNumber = InputUtil.readLong(scanner);

                        Account account =
                                accountService.getAccount(
                                        accountNumber);

                        System.out.println();
                        System.out.println(
                                "========== ACCOUNT REPORT ==========");

                        System.out.println(
                                "Account Number: " +
                                account.getAccountNumber());

                        System.out.println(
                                "Customer ID: " +
                                account.getCustomerId());

                        System.out.println(
                                "Account Type: " +
                                account.getAccountType());

                        System.out.println(
                                "Balance: ₹" +
                                account.getBalance());

                        System.out.println(
                                "Status: " +
                                account.getStatus());

                        System.out.println(
                                "====================================");
                        break;

                    case 4:

                        List<Account> accounts =
                                reportService.getAllAccounts();

                        System.out.println();
                        System.out.println(
                                "========= BANK SUMMARY REPORT =========");

                        System.out.println(
                                "Total Accounts: " +
                                reportService.getTotalAccounts());

                        System.out.println(
                                "Active Accounts: " +
                                reportService.getActiveAccounts());

                        System.out.println(
                                "Frozen Accounts: " +
                                reportService.getFrozenAccounts());

                        System.out.println();

                        for (Account a : accounts) {

                            System.out.println(
                                    "Account: " +
                                    a.getAccountNumber());

                            System.out.println(
                                    "Type: " +
                                    a.getAccountType());

                            System.out.println(
                                    "Balance: ₹" +
                                    a.getBalance());

                            System.out.println(
                                    "Status: " +
                                    a.getStatus());

                            System.out.println(
                                    "----------------------------------------");
                        }

                        break;

                    case 5:

                        System.out.println(
                                "Logged out successfully!");

                        running = false;
                        break;

                    default:

                        System.out.println(
                                "Invalid choice!");
                }

            } catch (AccountNotFoundException e) {

                System.out.println(
                        "Operation failed: " +
                        e.getMessage());

            } catch (Exception e) {

                System.out.println(
                        "Invalid input: " +
                        e.getMessage());
            }
        }
    }
}