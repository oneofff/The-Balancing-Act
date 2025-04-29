package com.pluralsight.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleStringReader {

    public static final Scanner scanner = new Scanner(System.in);

    public static double getPositiveDouble() {
        double value;
        while (true) {
            try {
                value = scanner.nextDouble();
                if (value <= 0) {
                    ScreenUtils.printlnWithMargins("Invalid input. Value should be greater than 0.");
                    ScreenUtils.printWithMargins("");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                ScreenUtils.printlnWithMargins("Invalid input. Please enter a numeric value greater than 0.");
                scanner.nextLine();
            }
        }
    }

    public static int getIntInRange(int rangeStart, int rangeEnd) {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                if ( value < rangeStart || value > rangeEnd) {
                    ScreenUtils.printlnWithMargins("Invalid input. Value should be greater than 0.");
                    ScreenUtils.printWithMargins("");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                ScreenUtils.printlnWithMargins("Invalid input. Please enter a numeric value greater than 0.");
                scanner.nextLine();
            }
        }
    }
}
