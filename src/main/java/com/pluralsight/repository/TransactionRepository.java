package com.pluralsight.repository;

import com.pluralsight.model.Transaction;

import java.util.List;

public interface TransactionRepository {
   void save(Transaction transaction);

    List<Transaction> getDeposits();

    List<Transaction> getAllTransactions();

    List<Transaction> getPayments();
}
