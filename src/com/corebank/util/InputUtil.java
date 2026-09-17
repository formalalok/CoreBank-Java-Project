package com.corebank.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

    public static int readInt(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Invalid input! Please enter a number.");
            scanner.next();
        }
    }

    public static long readLong(Scanner scanner) {
        while (true) {
            if (scanner.hasNextLong()) {
                return scanner.nextLong();
            }

            System.out.println("Invalid input! Please enter a valid account number.");
            scanner.next();
        }
    }

    public static BigDecimal readBigDecimal(Scanner scanner) {
        while (true) {
            if (scanner.hasNextBigDecimal()) {
                return scanner.nextBigDecimal();
            }

            System.out.println("Invalid amount! Please enter a valid number.");
            scanner.next();
        }
    }
}