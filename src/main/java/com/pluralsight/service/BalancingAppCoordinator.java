package com.pluralsight.service;

import com.pluralsight.ui.entryexit.SeeYouScreen;
import com.pluralsight.ui.entryexit.WelcomeScreen;
import com.pluralsight.ui.menu.HomeScreenMenu;
import com.pluralsight.ui.menu.LedgerScreenMenu;
import com.pluralsight.ui.menu.MenuEntry;
import com.pluralsight.ui.menu.ReportScreenMenu;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.Arrays;

public class BalancingAppCoordinator {

    public static void start() {
        WelcomeScreen.print();
        homeScreenFlow();
    }

    public static void homeScreenFlow() {
        while (true) {
            HomeScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(HomeScreenMenu.amountOfOptions());
            performAction(option, HomeScreenMenu.MenuOption.class);
        }
    }

    public static void ledgerScreenFlow() {
        while (true) {
            LedgerScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(LedgerScreenMenu.amountOfOptions());
            performAction(option, LedgerScreenMenu.MenuOption.class);
        }
    }

    public static void reportScreenFlow() {
        while (true) {
            ReportScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(ReportScreenMenu.amountOfOptions());
            performAction(option, ReportScreenMenu.MenuOption.class);
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
        SeeYouScreen.print();
        System.exit(0);
    }
}
