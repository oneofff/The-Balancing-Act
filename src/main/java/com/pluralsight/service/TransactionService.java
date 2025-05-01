package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.ui.AddTransactionScreen;
import com.pluralsight.ui.dto.TransactionSearchCriteria;

import java.util.List;

public class TransactionService {
    private static final TransactionRepository transactionRepository;

    static {
        transactionRepository = CsvTransactionRepository.getInstance();
    }

    public static void makePayment() {
        Transaction transaction = AddTransactionScreen.addTransaction(false);
        transactionRepository.save(transaction);
    }

    public static void addDeposit() {
        Transaction transaction = AddTransactionScreen.addTransaction(true);
        transactionRepository.save(transaction);
    }

    public static List<Transaction> customSearch(TransactionSearchCriteria criteria) {
        return transactionRepository.customSearch(criteria);
    }
}
