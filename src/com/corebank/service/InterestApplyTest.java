package com.corebank.service;

import java.math.BigDecimal;

public class InterestApplyTest {

    public static void main(String[] args) {

        InterestService service =
                new InterestService();

        try {

            service.applyInterest(
                    1001001,
                    new BigDecimal("4.00"));

        } catch (Exception e) {

            System.out.println(
                    "Interest application failed!");

            System.out.println(
                    e.getMessage());
        }
    }
}