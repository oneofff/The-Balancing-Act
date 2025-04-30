package com.pluralsight.utils;

import com.pluralsight.model.Transaction;

import java.time.format.DateTimeFormatter;
import java.util.List;

public final class TransactionPrinter {

    private static final String[] HEADERS =
            {"Date", "Time", "Description", "Vendor", "Amount"};

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private TransactionPrinter() {
    }

    public static void printTransactions(List<Transaction> transactions, String title) {

        int dateW = HEADERS[0].length();
        int timeW = HEADERS[1].length();
        int descW = HEADERS[2].length();
        int vendW = HEADERS[3].length();
        int amtW = HEADERS[4].length();

        for (Transaction t : transactions) {
            dateW = Math.max(dateW, t.getDate().toString().length());
            timeW = Math.max(timeW, TIME_FMT.format(t.getTime()).length());
            descW = Math.max(descW, t.getDescription().length());
            vendW = Math.max(vendW, t.getVendor().length());

            String amt = String.format("%.2f", t.getAmount().getDoubleValue());
            amtW = Math.max(amtW, amt.length());
        }

        String headerRow = "| %-" + dateW + "s | %-" + timeW + "s | %-" + descW +
                "s | %-" + vendW + "s | %" + amtW + "s |%n";

        String separator = "+" + "-".repeat(dateW + 2) + "+"
                + "-".repeat(timeW + 2) + "+"
                + "-".repeat(descW + 2) + "+"
                + "-".repeat(vendW + 2) + "+"
                + "-".repeat(amtW + 2) + "+";


        String shift = " ".repeat(headerRow.length() - title.length());
        ScreenUtils.printlnWithMargins(shift + title);
        ScreenUtils.printlnWithMargins("");

        //header
        ScreenUtils.printlnWithMargins(separator);
        ScreenUtils.printfWithMargins(headerRow, (Object[]) HEADERS);
        ScreenUtils.printlnWithMargins(separator);

        //rows
        for (Transaction t : transactions) {
            ScreenUtils.printfWithMargins(headerRow,
                    t.getDate(),
                    TIME_FMT.format(t.getTime()),
                    t.getDescription(),
                    t.getVendor(),
                    String.format("%.2f", t.getAmount().getDoubleValue()));
        }

        //footer
        ScreenUtils.printlnWithMargins(separator);
        int linesPrinted = transactions.size() + 5;
        ScreenUtils.evenFromTheBottom(linesPrinted);
    }
}
