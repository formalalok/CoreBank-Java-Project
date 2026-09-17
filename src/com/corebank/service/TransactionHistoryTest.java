package com.corebank.service;

import com.corebank.model.Transaction;

import java.util.List;

public class TransactionHistoryTest {

    public static void main(String[] args) {

        TransactionService transactionService =
                new TransactionService();

        List<Transaction> transactions =
                transactionService.getTransactionHistory(1001001);

        System.out.println("Transaction History");
        System.out.println("-------------------");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction t : transactions) {

            System.out.println("Transaction ID: "
                    + t.getTxnId());

            System.out.println("From Account: "
                    + t.getFromAccount());

            System.out.println("To Account: "
                    + t.getToAccount());

            System.out.println("Amount: "
                    + t.getAmount());

            System.out.println("Type: "
                    + t.getType());

            System.out.println("Status: "
                    + t.getStatus());

            System.out.println("Time: "
                    + t.getTimestamp());

            System.out.println("-------------------");
        }
    }
}