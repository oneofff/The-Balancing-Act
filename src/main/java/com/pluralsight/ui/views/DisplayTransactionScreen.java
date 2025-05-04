package com.pluralsight.ui.views;

import com.pluralsight.model.Transaction;
import com.pluralsight.service.TransactionService;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;
import java.util.function.Supplier;

public class DisplayTransactionScreen {

    private static void display(Supplier<List<Transaction>> dataSupplier, String title) {
        List<Transaction> transactionsToDisplay = dataSupplier.get();
        ScreenUtils.printTransactionsReverse(transactionsToDisplay, title);
    }

    public static void printAll() {
        display(TransactionService::getAllTransactions, "All Transactions");
    }

    public static void printDeposits() {
        display(TransactionService::getDeposits, "Deposits");
    }

    public static void printPayments() {
        display(TransactionService::getPayments, "Payments");
    }

    public static void printMonthToDate() {
        display(TransactionService::getMonthToDate, "Month to Date");
    }

    public static void printPreviousMonth() {
        display(TransactionService::getPreviousMonth, "Previous Month");
    }

    public static void printYearToDate() {
        display(TransactionService::getYearToDate, "Year to Date");
    }

    public static void printPreviousYear() {
        display(TransactionService::getPreviousYear, "Previous Year");
    }
}
