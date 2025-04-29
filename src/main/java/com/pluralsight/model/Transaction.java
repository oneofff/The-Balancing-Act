package com.pluralsight.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(double amount, String vendor, String description) {
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public Transaction(String cvsLine) {
        String[] fields = cvsLine.split("\\|");
        this.date = LocalDate.parse(fields[0]);
        this.time = LocalTime.parse(fields[1]);
        this.description = fields[2];
        this.vendor = fields[3];
        this.amount = Double.parseDouble(fields[4]);
    }

    public String toCsv() {
        return String.format("%s|%s|%s|%s|%.2f", date, time.format(DateTimeFormatter.ofPattern("HH:mm:ss")), description, vendor, amount);
    }
}
