package com.corebank.concurrent;

import com.corebank.service.TransactionService;

import java.math.BigDecimal;

public class TransferTask implements Runnable {

    private long fromAccount;
    private long toAccount;
    private BigDecimal amount;

    public TransferTask(long fromAccount,
                        long toAccount,
                        BigDecimal amount) {

        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {

        TransactionService service =
                new TransactionService();

        try {

            service.transfer(
                    fromAccount,
                    toAccount,
                    amount
            );

            System.out.println(
                    Thread.currentThread().getName()
                    + " transfer successful: ₹"
                    + amount
            );

        } catch (Exception e) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " transfer failed: "
                    + e.getMessage()
            );
        }
    }
}