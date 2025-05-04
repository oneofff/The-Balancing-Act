package com.pluralsight.ui.forms;

import com.pluralsight.model.Transaction;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

public class AddTransactionScreen {
    public static Transaction addTransaction(boolean isDeposit) {
        if (isDeposit) {

            ScreenUtils.printOnCenterOfTheScreen("Add deposit:");
        } else {
            ScreenUtils.printOnCenterOfTheScreen("Make payment:");
        }
        ScreenUtils.printlnWithMargins("Enter the amount: ");
        double amount = ConsoleStringReader.getPositiveDoubleWithMargin();
        ScreenUtils.printlnWithMargins("Enter vendor name: ");
        String vendor = ConsoleStringReader.getStringWithMargin();
        ScreenUtils.printlnWithMargins("Enter description: ");
        String description = ConsoleStringReader.getStringWithMargin();
        if (!isDeposit && amount > 0) {
            amount = -amount;
        }
        return new Transaction(amount, vendor, description);
    }
}
