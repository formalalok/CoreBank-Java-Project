package com.corebank.concurrent;

import java.math.BigDecimal;

public class ConcurrencyTest {

    public static void main(String[] args)
            throws InterruptedException {

        Thread t1 = new Thread(
                new TransferTask(
                        1001001,
                        1001002,
                        new BigDecimal("500.00")),
                "Thread-1"
        );

        Thread t2 = new Thread(
                new TransferTask(
                        1001001,
                        1001002,
                        new BigDecimal("700.00")),
                "Thread-2"
        );

        Thread t3 = new Thread(
                new TransferTask(
                        1001001,
                        1001002,
                        new BigDecimal("300.00")),
                "Thread-3"
        );

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println();
        System.out.println(
                "Concurrent transfer test completed.");
    }
}