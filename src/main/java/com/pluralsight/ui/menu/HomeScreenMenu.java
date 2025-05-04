package com.pluralsight.ui.menu;

import com.pluralsight.service.BalancingAppCoordinator;
import com.pluralsight.service.TransactionService;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class HomeScreenMenu {


    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.ADD_DEPOSIT.getRepresentation(),
                MenuOption.MAKE_PAYMENT.getRepresentation(),
                MenuOption.LEDGER_MENU.getRepresentation(),
                MenuOption.EXIT.getRepresentation()));
    }

    @Getter
    @AllArgsConstructor
    public enum MenuOption implements MenuEntry {
        ADD_DEPOSIT(1, "Add deposit", TransactionService::addDeposit),
        MAKE_PAYMENT(2, "Make payment", TransactionService::makePayment),
        LEDGER_MENU(3, "Ledger menu", BalancingAppCoordinator::ledgerScreenFlow),
        EXIT(0, "Exit", BalancingAppCoordinator::exit);

        private final int value;
        private final String name;
        private final Runnable action;

        public String getRepresentation() {
            return value + ". " + name;
        }

        public static int getAmountOfOptions() {
            return values().length;
        }
    }
}
