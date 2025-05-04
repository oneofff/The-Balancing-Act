package com.pluralsight.repository;

import com.pluralsight.config.AppConfig;
import com.pluralsight.model.Transaction;
import com.pluralsight.ui.dto.TransactionSearchCriteria;
import com.pluralsight.utils.files.FileReaderUtils;
import com.pluralsight.utils.files.FileWriterUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CsvTransactionRepository implements TransactionRepository, AutoCloseable {

    private final List<Transaction> transactions;

    private final FileWriterUtils fileWriterUtils;
    private static final String HEADER = "date|time|description|vendor|amount";
    private final String csvFilePath = AppConfig.getCsvFilePath();

    private CsvTransactionRepository() {
        this.fileWriterUtils = new FileWriterUtils(csvFilePath);
        try (FileReaderUtils fileReaderUtils = new FileReaderUtils(csvFilePath)) {
            if (fileReaderUtils.isEmpty()) {
                fileWriterUtils.writeLine(HEADER);
            }
            this.transactions = readTransactions(fileReaderUtils);
        }
    }

    public static CsvTransactionRepository getInstance() {
        return SingletonHolder.instance;
    }

    private List<Transaction> readTransactions(FileReaderUtils fileReaderUtils) {
        List<Transaction> transactions = new LinkedList<>();
        String header = fileReaderUtils.readLine();
        if (header == null || !header.equals(HEADER))
            throw new RuntimeException("Invalid header in " + csvFilePath);
        String line;
        while ((line = fileReaderUtils.readLine()) != null) {
            Transaction transaction = new Transaction(line);
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public void save(Transaction transaction) {
        fileWriterUtils.writeLine(transaction.toCsv());
        this.transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public List<Transaction> getDeposits() {
        return this.transactions.stream()
                .filter(t -> t.getAmount().getDoubleValue() > 0)
                .toList();
    }

    @Override
    public List<Transaction> getMonthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(monthStart)
                        && !t.getDate().isAfter(today))
                .toList();
    }

    @Override
    public List<Transaction> getPayments() {
        return this.transactions.stream()
                .filter(t -> t.getAmount().getDoubleValue() < 0)
                .toList();
    }

    @Override
    public List<Transaction> getPreviousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate prevMonthStart = today.minusMonths(1).withDayOfMonth(1);
        LocalDate thisMonthStart = today.withDayOfMonth(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(prevMonthStart)
                        && t.getDate().isBefore(thisMonthStart))
                .toList();
    }

    @Override
    public List<Transaction> getPreviousYear() {
        LocalDate today = LocalDate.now();
        LocalDate startOfLastYear = today.minusYears(1).withDayOfYear(1);
        LocalDate startOfThisYear = today.withDayOfYear(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(startOfLastYear)
                        && t.getDate().isBefore(startOfThisYear))
                .toList();
    }

    @Override
    public List<Transaction> getYearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(startOfYear)
                        && !t.getDate().isAfter(today))
                .toList();
    }

    @Override
    public void close() {
        if (fileWriterUtils != null) {
            fileWriterUtils.close();
        }
    }

    @Override
    public List<Transaction> customSearch(TransactionSearchCriteria transaction) {
        return transactions.stream()
                .filter(transaction.toPredicate())
                .toList();
    }

    private static final class SingletonHolder {
        private static final CsvTransactionRepository instance = new CsvTransactionRepository();
    }
}
