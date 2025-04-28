package com.pluralsight.utils;

import java.io.IOException;

public class ScreenUtils {
    public static void waitTillPressEnter() {
        printOnCenterOfTheScreen("Press Enter to continue...");
        while (true) {
            try {
                if (System.in.read() == '\n') {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printWithMargins(String message) {
        System.out.println("\t".repeat(15) + message);
    }

    public static void printOnCenterOfTheScreen(String message) {
        System.out.println("\t".repeat(18) + message);
    }

    public static void cls() {
        // print new line 10 times
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }
}
