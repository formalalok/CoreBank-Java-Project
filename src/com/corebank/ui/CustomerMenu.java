package com.corebank.ui;

import com.corebank.dao.CustomerDAO;
import com.corebank.model.Account;
import com.corebank.model.Customer;
import com.corebank.model.Transaction;
import com.corebank.model.User;
import com.corebank.service.AccountService;
import com.corebank.service.InterestService;
import com.corebank.service.TransactionService;
import com.corebank.util.InputUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    public static void show(Scanner scanner, User user) {

        AccountService accountService =
                new AccountService();

        TransactionService transactionService =
                new TransactionService();

        InterestService interestService =
                new InterestService();

        CustomerDAO customerDAO =
                new CustomerDAO();

        Customer customer =
                customerDAO.getCustomerByUserId(
                        user.getUserId());

        if (customer == null) {
            System.out.println(
                    "Customer profile not found!");
            return;
        }

        long accountNumber;

        while (true) {

            System.out.print(
                    "Enter your account number: ");

            try {

                accountNumber =
                        InputUtil.readLong(scanner);

                Account account =
                        accountService.getAccount(
                                accountNumber);

                if (!accountService.belongsToCustomer(
                        accountNumber,
                        customer.getCustomerId())) {

                    System.out.println(
                            "Access denied: This account does not belong to you.");

                    continue;
                }

                System.out.println(
                        "Account selected: " +
                        accountNumber);

                System.out.println(
                        "Account Type: " +
                        account.getAccountType());

                break;

            } catch (Exception e) {

                System.out.println(
                        "Invalid account: " +
                        e.getMessage());
            }
        }

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println(
                    "============ CUSTOMER MENU ============");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Apply Savings Interest");
            System.out.println("7. Logout");
            System.out.println(
                    "=======================================");
            System.out.print("Enter choice: ");

            int choice =
                    InputUtil.readInt(scanner);

            try {

                switch (choice) {

                    case 1:

                        BigDecimal balance =
                                accountService.getBalance(
                                        accountNumber);

                        System.out.println(
                                "Current Balance: ₹" +
                                balance);

                        break;

                    case 2:

                        System.out.print(
                                "Enter deposit amount: ");

                        BigDecimal depositAmount =
                                InputUtil.readBigDecimal(
                                        scanner);

                        accountService.deposit(
                                accountNumber,
                                depositAmount);

                        System.out.println(
                                "Deposit successful!");

                        break;

                    case 3:

                        System.out.print(
                                "Enter withdrawal amount: ");

                        BigDecimal withdrawAmount =
                                InputUtil.readBigDecimal(
                                        scanner);

                        accountService.withdraw(
                                accountNumber,
                                withdrawAmount);

                        System.out.println(
                                "Withdrawal successful!");

                        break;

                    case 4:

                        System.out.print(
                                "Enter destination account: ");

                        long destinationAccount =
                                InputUtil.readLong(
                                        scanner);

                        System.out.print(
                                "Enter transfer amount: ");

                        BigDecimal transferAmount =
                                InputUtil.readBigDecimal(
                                        scanner);

                        if (!accountService.belongsToCustomer(
                                accountNumber,
                                customer.getCustomerId())) {

                            System.out.println(
                                    "Access denied.");

                            break;
                        }

                        transactionService.transfer(
                                accountNumber,
                                destinationAccount,
                                transferAmount);

                        System.out.println(
                                "Transfer successful!");

                        break;

                    case 5:

                        List<Transaction> transactions =
                                transactionService
                                .getTransactionHistory(
                                        accountNumber);

                        System.out.println();
                        System.out.println(
                                "========== TRANSACTION HISTORY ==========");

                        if (transactions.isEmpty()) {

                            System.out.println(
                                    "No transactions found.");

                        } else {

                            for (Transaction t :
                                    transactions) {

                                System.out.println(
                                        "Transaction ID: " +
                                        t.getTxnId());

                                System.out.println(
                                        "Type: " +
                                        t.getType());

                                System.out.println(
                                        "Amount: ₹" +
                                        t.getAmount());

                                System.out.println(
                                        "Status: " +
                                        t.getStatus());

                                System.out.println(
                                        "Time: " +
                                        t.getTimestamp());

                                System.out.println(
                                        "----------------------------------------");
                            }
                        }

                        break;

                    case 6:

                        System.out.print(
                                "Enter annual interest rate (%): ");

                        BigDecimal interestRate =
                                InputUtil.readBigDecimal(
                                        scanner);

                        interestService.applyInterest(
                                accountNumber,
                                interestRate);

                        break;

                    case 7:

                        System.out.println(
                                "Logged out successfully!");

                        running = false;

                        break;

                    default:

                        System.out.println(
                                "Invalid choice!");

                }

            } catch (Exception e) {

                System.out.println(
                        "Operation failed: " +
                        e.getMessage());
            }
        }
    }
}