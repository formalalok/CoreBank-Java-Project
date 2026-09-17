package com.corebank.service;

import java.math.BigDecimal;

public class TransactionServiceTest {

    public static void main(String[] args) {

        TransactionService transactionService =
                new TransactionService();

        try {

            transactionService.transfer(
                    1001001,
                    1001002,
                    new BigDecimal("1000.00")
            );

            System.out.println("Transfer successful!");

        } catch (Exception e) {

            System.out.println("Transfer failed!");
            System.out.println(e.getMessage());
        }
    }
}