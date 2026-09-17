package com.corebank.service;

import com.corebank.dao.AccountDAO;
import com.corebank.dao.TransactionDAO;
import com.corebank.exception.AccountNotFoundException;
import com.corebank.exception.AccountFrozenException;
import com.corebank.model.Account;
import com.corebank.model.Transaction;
import com.corebank.util.DBConnection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;

public class InterestService {

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public InterestService() {
        accountDAO = new AccountDAO();
        transactionDAO = new TransactionDAO();
    }

    public BigDecimal calculateInterest(
            long accountNumber,
            BigDecimal annualRate)
            throws AccountNotFoundException {

        if (annualRate == null ||
            annualRate.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Interest rate must be greater than zero!");
        }

        Account account =
                accountDAO.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found!");
        }

        if (!"SAVINGS".equals(account.getAccountType())) {
            throw new IllegalArgumentException(
                    "Interest is available only for Savings accounts!");
        }

        return account.getBalance()
                .multiply(annualRate)
                .divide(
                        new BigDecimal("100"),
                        2,
                        RoundingMode.HALF_UP);
    }

    public void applyInterest(
            long accountNumber,
            BigDecimal annualRate)
            throws Exception {

        if (annualRate == null ||
            annualRate.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Interest rate must be greater than zero!");
        }

        try (Connection con =
                     DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {

                Account account =
                        accountDAO.getAccount(
                                con,
                                accountNumber);

                if (account == null) {
                    throw new AccountNotFoundException(
                            "Account not found!");
                }

                if ("FROZEN".equals(account.getStatus())) {
                    throw new AccountFrozenException(
                            "Account is frozen!");
                }

                if (!"SAVINGS".equals(
                        account.getAccountType())) {

                    throw new IllegalArgumentException(
                            "Interest is available only for Savings accounts!");
                }

                BigDecimal interest =
                        account.getBalance()
                        .multiply(annualRate)
                        .divide(
                                new BigDecimal("100"),
                                2,
                                RoundingMode.HALF_UP);

                BigDecimal newBalance =
                        account.getBalance()
                        .add(interest);

                accountDAO.updateBalance(
                        con,
                        accountNumber,
                        newBalance);

                Transaction transaction =
                        new Transaction(
                                0,
                                null,
                                accountNumber,
                                interest,
                                "INTEREST",
                                null,
                                "SUCCESS"
                        );

                transactionDAO.saveTransaction(
                        con,
                        transaction);

                con.commit();

                System.out.println(
                        "Interest applied successfully!");

                System.out.println(
                        "Interest: ₹" + interest);

                System.out.println(
                        "New Balance: ₹" + newBalance);

            } catch (Exception e) {

                con.rollback();
                throw e;
            }
        }
    }
}