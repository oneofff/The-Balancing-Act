package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.service.TransactionService;
import com.pluralsight.utils.ScreenUtils;

import java.util.List;

public class DisplayTransactionScreen {


    public static void printAll() {
        List<Transaction> allTransactions = TransactionService.getAllTransactions();
        ScreenUtils.printTransactions(allTransactions, "All Transactions");
    }


    public static void printDeposits() {
        List<Transaction> deposits =  TransactionService.getDeposits();
        ScreenUtils.printTransactions(deposits, "Deposits");
    }

    public static void printPayments() {
        List<Transaction> payments = TransactionService.getPayments();
        ScreenUtils.printTransactions(payments, "Payments");
    }

    public static void printMonthToDate() {
        List<Transaction> monthToDate = TransactionService.getMonthToDate();
        ScreenUtils.printTransactions(monthToDate, "Month to Date");
    }

    public static void printPreviousMonth() {
        List<Transaction> previousMonth = TransactionService.getPreviousMonth();
        ScreenUtils.printTransactions(previousMonth, "Previous Month");
    }

    public static void printYearToDate() {
        List<Transaction> yearToDate = TransactionService.getYearToDate();
        ScreenUtils.printTransactions(yearToDate, "Year to Date");
    }

    public static void printPreviousYear() {
        List<Transaction> previousYear = TransactionService.getPreviousYear();
        ScreenUtils.printTransactions(previousYear, "Previous Year");
    }
}
