package com.pluralsight.repository;

import com.pluralsight.config.AppConfig;
import com.pluralsight.model.Transaction;
import com.pluralsight.utils.files.FileReaderUtils;
import com.pluralsight.utils.files.FileWriterUtils;

import java.util.LinkedList;
import java.util.List;

public class CsvTransactionRepository implements TransactionRepository {

    private final List<Transaction> transactions;
    private final FileReaderUtils fileReaderUtils;
    private final FileWriterUtils fileWriterUtils;
    private static CsvTransactionRepository instance;
    private static final String HEADER = "date|time|description|vendor|amount";

    private CsvTransactionRepository() {
        this.fileWriterUtils = new FileWriterUtils(AppConfig.getCsvFilePath());
        this.fileReaderUtils = new FileReaderUtils(AppConfig.getCsvFilePath());

        if (fileReaderUtils.isEmpty()) {
            fileWriterUtils.writeLine(HEADER);
        }
        this.transactions = readTransactions();
    }

    public static CsvTransactionRepository getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new CsvTransactionRepository();
        return instance;
    }

    @Override
    public void save(Transaction transaction) {
        this.transactions.add(transaction);
        fileWriterUtils.writeLine(transaction.toCsv());
    }

    @Override
    public List<Transaction> getDeposits() {
        return this.transactions.stream()
                .filter(t -> t.getAmount().getDoubleValue() > 0)
                .toList();
    }

    private List<Transaction> readTransactions() {
        List<Transaction> transactions = new LinkedList<>();
        String header = fileReaderUtils.readLine(); // skip header
        String line;
        while ((line = fileReaderUtils.readLine()) != null) {
            Transaction transaction = new Transaction(line);
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    @Override
    public List<Transaction> getPayments() {
        return this.transactions.stream()
                .filter(t -> t.getAmount().getDoubleValue() < 0)
                .toList();
    }

    public void close() {
        fileWriterUtils.close();
        fileReaderUtils.close();
    }

}
