package com.pluralsight.ui;

import com.pluralsight.service.BalancingAppCoordinator;
import com.pluralsight.utils.ScreenUtils;
import lombok.Getter;

import java.util.List;

public class LedgerScreen {


    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.DISPLAY_ALL.getRepresentation(),
                MenuOption.DISPLAY_DEPOSITS.getRepresentation(),
                MenuOption.DISPLAY_PAYMENTS.getRepresentation(),
                MenuOption.REPORTS.getRepresentation(),
                MenuOption.BACK.getRepresentation()));
    }

    @Getter
    public enum MenuOption implements MenuEntry {
        DISPLAY_ALL(1, "Display all transactions", DisplayTransactionScreen::printAll),
        DISPLAY_DEPOSITS(2, "Display deposits", DisplayTransactionScreen::printDeposits),
        DISPLAY_PAYMENTS(3, "Display payments", DisplayTransactionScreen::printPayments),
        REPORTS(4, "Reports", BalancingAppCoordinator::reportScreenFlow),
        BACK(5, "Back", BalancingAppCoordinator::homeScreenFlow);

        private final int value;
        private final String name;
        private final Runnable action;

        MenuOption(int value, String name, Runnable action) {
            this.value = value;
            this.name = name;
            this.action = action;
        }

        public static int getAmountOfOptions() {
            return MenuOption.values().length;
        }

        public String getRepresentation() {
            return value + ". " + name;
        }
    }
}
