package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.utils.ScreenUtils;

import java.util.List;

public class DisplayTransactionScreen {
    private static final CsvTransactionRepository transactionRepository = CsvTransactionRepository.getInstance();

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
}
