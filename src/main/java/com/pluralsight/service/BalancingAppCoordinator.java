package com.pluralsight.service;

import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.ui.*;
import com.pluralsight.utils.ConsoleStringReader;
import com.pluralsight.utils.ScreenUtils;

import java.util.Arrays;

public class BalancingAppCoordinator {

    public static void start() {
        WelcomeScreen.print();
        homeScreenFlow();
    }

    public static void homeScreenFlow() {
        while (true) {
            HomeScreen.print();
            int option = ScreenUtils.askForMenuOptionsInput(HomeScreen.amountOfOptions());
            performAction(option, HomeScreen.MenuOption.class);
        }
    }

    public static void ledgerScreenFlow() {
        while (true) {
            LedgerScreen.print();
            int option = ScreenUtils.askForMenuOptionsInput(LedgerScreen.amountOfOptions());
            performAction(option, LedgerScreen.MenuOption.class);
        }
    }

    public static void reportScreenFlow() {
        while (true) {
            ReportScreen.print();
            int option = ScreenUtils.askForMenuOptionsInput(ReportScreen.amountOfOptions());
            performAction(option, ReportScreen.MenuOption.class);
        }
    }

    public static <T extends Enum<T> & MenuEntry> void performAction(int option, Class<T> menu) {
        T menuOption = Arrays.stream(menu.getEnumConstants())
                .filter(entry -> entry.getValue() == option)
                .findFirst()
                .orElse(null);
        if (menuOption != null) {
            ScreenUtils.cls();
            menuOption.getAction().run();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    public static void exit() {
        closeResources();
        SeeYouScreen.print();
        System.exit(0);
    }

    private static void closeResources() {
        try {
            ConsoleStringReader.close();
            CsvTransactionRepository.getInstance().close();
        } catch (Exception e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
