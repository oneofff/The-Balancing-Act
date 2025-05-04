package com.pluralsight.ui.menu;

import com.pluralsight.service.BalancingAppCoordinator;
import com.pluralsight.ui.views.DisplayTransactionScreen;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class LedgerScreenMenu {


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
    @AllArgsConstructor
    public enum MenuOption implements MenuEntry {
        DISPLAY_ALL(1, "Display all transactions", DisplayTransactionScreen::printAll),
        DISPLAY_DEPOSITS(2, "Display deposits", DisplayTransactionScreen::printDeposits),
        DISPLAY_PAYMENTS(3, "Display payments", DisplayTransactionScreen::printPayments),
        REPORTS(4, "Reports", BalancingAppCoordinator::reportScreenFlow),
        BACK(0, "Back", BalancingAppCoordinator::homeScreenFlow);

        private final int value;
        private final String name;
        private final Runnable action;

        public static int getAmountOfOptions() {
            return MenuOption.values().length;
        }

        public String getRepresentation() {
            return value + ". " + name;
        }
    }
}
