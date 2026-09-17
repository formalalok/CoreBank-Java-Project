package com.corebank.service;

import com.corebank.dao.AccountDAO;
import com.corebank.dao.TransactionDAO;
import com.corebank.exception.AccountFrozenException;
import com.corebank.exception.AccountNotFoundException;
import com.corebank.exception.InsufficientBalanceException;
import com.corebank.exception.InvalidAmountException;
import com.corebank.model.Account;
import com.corebank.model.Transaction;
import com.corebank.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class TransactionService {

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public TransactionService() {
        accountDAO = new AccountDAO();
        transactionDAO = new TransactionDAO();
    }

    public void transfer(long fromAccount,
                         long toAccount,
                         BigDecimal amount)
            throws Exception {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Transfer amount must be greater than zero!");
        }

        if (fromAccount == toAccount) {
            throw new InvalidAmountException(
                    "Source and destination accounts cannot be same!");
        }

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {

                long firstAccount = Math.min(fromAccount, toAccount);
                long secondAccount = Math.max(fromAccount, toAccount);

                Account first = accountDAO.getAccount(
                        con, firstAccount);

                Account second = accountDAO.getAccount(
                        con, secondAccount);

                if (first == null || second == null) {
                    throw new AccountNotFoundException(
                            "One or both accounts not found!");
                }

                Account source;

                if (fromAccount == firstAccount)
                    source = first;
                else
                    source = second;

                Account destination;

                if (toAccount == firstAccount)
                    destination = first;
                else
                    destination = second;

                if ("FROZEN".equals(source.getStatus()) ||
                    "FROZEN".equals(destination.getStatus())) {

                    throw new AccountFrozenException(
                            "One of the accounts is frozen!");
                }

                if (source.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientBalanceException(
                            "Insufficient balance!");
                }

                BigDecimal sourceBalance =
                        source.getBalance().subtract(amount);

                BigDecimal destinationBalance =
                        destination.getBalance().add(amount);

                if (!accountDAO.updateBalance(
                        con,
                        fromAccount,
                        sourceBalance)) {

                    throw new RuntimeException(
                            "Source account update failed!");
                }

                if (!accountDAO.updateBalance(
                        con,
                        toAccount,
                        destinationBalance)) {

                    throw new RuntimeException(
                            "Destination account update failed!");
                }

                Transaction transaction = new Transaction(
                        0,
                        fromAccount,
                        toAccount,
                        amount,
                        "TRANSFER",
                        null,
                        "SUCCESS"
                );

                if (!transactionDAO.saveTransaction(
                        con, transaction)) {

                    throw new RuntimeException(
                            "Transaction record failed!");
                }

                con.commit();

            } catch (Exception e) {

                con.rollback();
                throw e;
            }
        }
    }

    public List<Transaction> getTransactionHistory(
            long accountNumber) {

        return transactionDAO.getTransactions(accountNumber);
    }
}