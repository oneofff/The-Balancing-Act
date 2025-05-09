package com.pluralsight.repository;

import com.pluralsight.model.Transaction;
import com.pluralsight.ui.dto.TransactionSearchCriteria;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> getDeposits();

    List<Transaction> getAllTransactions();

    List<Transaction> getPayments();

    List<Transaction> getMonthToDate();

    List<Transaction> getPreviousMonth();

    List<Transaction> getPreviousYear();

    List<Transaction> getYearToDate();

    List<Transaction> customSearch(TransactionSearchCriteria criteria);
}
