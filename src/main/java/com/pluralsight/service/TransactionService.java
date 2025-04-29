package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.ui.SeeYouScreen;
import com.pluralsight.utils.ConsoleStringReader;
import com.pluralsight.utils.ScreenUtils;

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
        System.out.println("Make payment");
        // Implement the logic for making a payment
    }

    public static void addDeposit() {
        ScreenUtils.printlnWithMargins("Add deposit");
        ScreenUtils.printlnWithMargins("Enter the amount to deposit: ");
        double amount = ConsoleStringReader.getPositiveDouble();
        ScreenUtils.printlnWithMargins("Enter vendor name: ");
        String vendor = ConsoleStringReader.scanner.next();
        ScreenUtils.printlnWithMargins("Enter description: ");
        String description = ConsoleStringReader.scanner.next();
        Transaction transaction = new Transaction(amount, vendor, description);
        transactionRepository.save(transaction);
    }
}
