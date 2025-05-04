package com.pluralsight.ui.menu;

import com.pluralsight.service.BalancingAppCoordinator;
import com.pluralsight.ui.forms.CustomSearchScreen;
import com.pluralsight.ui.views.DisplayTransactionScreen;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ReportScreenMenu {

    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(
                MenuOption.MOUTH_TO_DATE.getRepresentation(),
                MenuOption.PREVIOUS_MONTH.getRepresentation(),
                MenuOption.YEAR_TO_DATE.getRepresentation(),
                MenuOption.PREVIOUS_YEAR.getRepresentation(),
                MenuOption.CUSTOM_SEARCH.getRepresentation(),
                MenuOption.BACK.getRepresentation()));
    }

    @Getter
    @AllArgsConstructor
    public enum MenuOption implements MenuEntry {
        MOUTH_TO_DATE(1, "Month to date", DisplayTransactionScreen::printMonthToDate),
        PREVIOUS_MONTH(2, "Previous month", DisplayTransactionScreen::printPreviousMonth),
        YEAR_TO_DATE(3, "Year to date", DisplayTransactionScreen::printYearToDate),
        PREVIOUS_YEAR(4, "Previous year", DisplayTransactionScreen::printPreviousYear),
        CUSTOM_SEARCH(5, "Custom search", CustomSearchScreen::customSearch),
        BACK(0, "Back", BalancingAppCoordinator::ledgerScreenFlow);


        private final int value;
        private final String name;
        private final Runnable action;

        public static int getAmountOfOptions() {
            return (int) Arrays.stream(values()).count();
        }

        public String getRepresentation() {
            return value + ". " + name;
        }
    }
}
