package com.pluralsight.ui;

import com.pluralsight.utils.ScreenUtils;

public class WelcomeScreen {

    public static void print() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        ScreenUtils.printWithMargins("+=================================================+");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|              THE BALANCING ACT                  |");
        ScreenUtils.printWithMargins("|        Accounting Ledger Application            |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                                 |");
        ScreenUtils.printWithMargins("|                                 Stanley Karol   |");
        ScreenUtils.printWithMargins("|                                 Capstone - 2025 |");
        ScreenUtils.printWithMargins("|                                 YearUp United   |");
        ScreenUtils.printWithMargins("+=================================================+");
        System.out.println();
        System.out.println();
        System.out.println();
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

}
