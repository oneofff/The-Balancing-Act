package com.pluralsight.repository;

import com.pluralsight.model.Transaction;

public interface TransactionRepository {
   void save(Transaction transaction);
}
