package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.ui.SeeYouScreen;
import com.pluralsight.ui.TransactionScreen;

public class TransactionService {
    private static final TransactionRepository transactionRepository;

    static {
        transactionRepository = CsvTransactionRepository.getInstance();
    }

    public static void exit() {
        System.out.println("Exiting the application...");
        SeeYouScreen.print();
        System.exit(0);
    }

    public static void makePayment() {
        Transaction transaction = TransactionScreen.getTransaction(false);
        transactionRepository.save(transaction);
    }

    public static void addDeposit() {
        Transaction transaction = TransactionScreen.getTransaction(true);
        transactionRepository.save(transaction);
    }


}
