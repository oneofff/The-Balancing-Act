package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.utils.ScreenUtils;

import java.util.List;

public class DisplayTransactionScreen {
    private static final TransactionRepository transactionRepository = CsvTransactionRepository.getInstance();

    public static void printAll() {
        List<Transaction> allTransactions = transactionRepository.getAllTransactions();
        ScreenUtils.printTransactions(allTransactions, "All Transactions");
    }

    public static void printDeposits() {
        List<Transaction> deposits = transactionRepository.getDeposits();
        ScreenUtils.printTransactions(deposits, "Deposits");
    }

    public static void printPayments() {
        List<Transaction> payments = transactionRepository.getPayments();
        ScreenUtils.printTransactions(payments, "Payments");
    }

    public static void printMonthToDate() {
        List<Transaction> monthToDate = transactionRepository.getMonthToDate();
        ScreenUtils.printTransactions(monthToDate, "Month to Date");
    }

    public static void printPreviousMonth() {
        List<Transaction> previousMonth = transactionRepository.getPreviousMonth();
        ScreenUtils.printTransactions(previousMonth, "Previous Month");
    }

    public static void printYearToDate() {
        List<Transaction> yearToDate = transactionRepository.getYearToDate();
        ScreenUtils.printTransactions(yearToDate, "Year to Date");
    }

    public static void printPreviousYear() {
        List<Transaction> previousYear = transactionRepository.getPreviousYear();
        ScreenUtils.printTransactions(previousYear, "Previous Year");
    }
}
