package com.pluralsight.ui;

import com.pluralsight.service.TransactionService;
import com.pluralsight.utils.ConsoleStringReader;
import com.pluralsight.utils.ScreenUtils;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class HomeScreen {

    private static final Map<Integer, Runnable> actions = Map.of(
            1, TransactionService::addDeposit,
            2, TransactionService::makePayment,
            3, LedgerScreen::print,
            4, TransactionService::exit
    );

    @Getter
    enum MenuOption {
        ADD_DEPOSIT(1, "Add deposit"),
        MAKE_PAYMENT(2, "Make payment"),
        LEDGER_MENU(3, "Ledger menu"),
        EXIT(4, "Exit");

        private final int value;
        private final String name;

        MenuOption(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getRepresentation() {
            return value + ". " + name;
        }
    }


    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.ADD_DEPOSIT.getRepresentation(),
                MenuOption.MAKE_PAYMENT.getRepresentation(),
                MenuOption.LEDGER_MENU.getRepresentation(),
                MenuOption.EXIT.getRepresentation()));
    }


    public static int askForInput() {
        ScreenUtils.printOnCenterOfTheScreen("Please select an option: ");
        return ConsoleStringReader.getIntInRangeWithMargin(1, 4);
    }

    public static void performAction(int option) {
        Runnable action = actions.get(option);
        if (action != null) {
            ScreenUtils.cls();
            action.run();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }
}
