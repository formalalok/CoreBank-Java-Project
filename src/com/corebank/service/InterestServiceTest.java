package com.corebank.service;

import java.math.BigDecimal;

public class InterestServiceTest {

    public static void main(String[] args) {

        InterestService service =
                new InterestService();

        try {

            long accountNumber = 1001001;

            BigDecimal annualRate =
                    new BigDecimal("4.00");

            BigDecimal interest =
                    service.calculateInterest(
                            accountNumber,
                            annualRate);

            System.out.println("Savings Account Interest");
            System.out.println("------------------------");
            System.out.println("Account: " + accountNumber);
            System.out.println("Annual Rate: " + annualRate + "%");
            System.out.println("Interest: ₹" + interest);

        } catch (Exception e) {

            System.out.println(
                    "Interest calculation failed!");

            System.out.println(
                    e.getMessage());
        }
    }
}