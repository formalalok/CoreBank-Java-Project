package com.corebank.service;

import com.corebank.exception.AccountNotFoundException;
import com.corebank.exception.AccountFrozenException;
import com.corebank.exception.InsufficientBalanceException;
import com.corebank.exception.InvalidAmountException;

import java.math.BigDecimal;

public class AccountServiceTest {

    public static void main(String[] args) {

        AccountService accountService = new AccountService();

        try {

            System.out.println("Initial Balance: "
                    + accountService.getBalance(1001001));

            accountService.deposit(
                    1001001,
                    new BigDecimal("1000.00")
            );

            System.out.println("After Deposit: "
                    + accountService.getBalance(1001001));

            accountService.withdraw(
                    1001001,
                    new BigDecimal("500.00")
            );

            System.out.println("After Withdrawal: "
                    + accountService.getBalance(1001001));

        } catch (AccountNotFoundException |
                 InvalidAmountException |
                 InsufficientBalanceException |
                 AccountFrozenException e) {

            System.out.println(e.getMessage());
        }
    }
}