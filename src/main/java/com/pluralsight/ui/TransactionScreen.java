package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.utils.ConsoleStringReader;
import com.pluralsight.utils.ScreenUtils;

public class TransactionScreen {
    public static Transaction getTransaction(boolean isDeposit) {
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
        if (!isDeposit) {
            amount = -amount;
        }
        return new Transaction(amount, vendor, description);
    }
}
