package com.corebank.service;

import com.corebank.dao.AccountDAO;
import com.corebank.exception.AccountFrozenException;
import com.corebank.exception.AccountNotFoundException;
import com.corebank.exception.InsufficientBalanceException;
import com.corebank.exception.InvalidAmountException;
import com.corebank.model.Account;

import java.math.BigDecimal;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public Account getAccount(long accountNumber)
            throws AccountNotFoundException {

        Account account = accountDAO.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found!");
        }

        return account;
    }

    public BigDecimal getBalance(long accountNumber)
            throws AccountNotFoundException {

        return getAccount(accountNumber).getBalance();
    }

    public void deposit(long accountNumber, BigDecimal amount)
            throws AccountNotFoundException,
                   InvalidAmountException,
                   AccountFrozenException {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero!");
        }

        Account account = getAccount(accountNumber);

        if ("FROZEN".equals(account.getStatus())) {
            throw new AccountFrozenException("Account is frozen!");
        }

        BigDecimal newBalance = account.getBalance().add(amount);

        if (!accountDAO.updateBalance(accountNumber, newBalance)) {
            throw new RuntimeException("Deposit failed!");
        }
    }

    public void withdraw(long accountNumber, BigDecimal amount)
            throws AccountNotFoundException,
                   InvalidAmountException,
                   InsufficientBalanceException,
                   AccountFrozenException {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero!");
        }

        Account account = getAccount(accountNumber);

        if ("FROZEN".equals(account.getStatus())) {
            throw new AccountFrozenException("Account is frozen!");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(
                    "Insufficient balance!");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);

        if (!accountDAO.updateBalance(accountNumber, newBalance)) {
            throw new RuntimeException("Withdrawal failed!");
        }
    }

    public void freezeAccount(long accountNumber)
            throws AccountNotFoundException {

        getAccount(accountNumber);

        if (!accountDAO.updateStatus(accountNumber, "FROZEN")) {
            throw new RuntimeException("Unable to freeze account!");
        }
    }

    public void unfreezeAccount(long accountNumber)
            throws AccountNotFoundException {

        getAccount(accountNumber);

        if (!accountDAO.updateStatus(accountNumber, "ACTIVE")) {
            throw new RuntimeException("Unable to unfreeze account!");
        }
    }
public boolean belongsToCustomer(long accountNumber,
                                 int customerId) {

    return accountDAO.belongsToCustomer(
            accountNumber,
            customerId);
}
}