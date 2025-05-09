package com.pluralsight.utils.console;

import com.pluralsight.model.Transaction;

import java.time.format.DateTimeFormatter;
import java.util.List;

public final class TransactionPrinter {

    public static final String YELLOW = "\033[0;33m";
    public static final String GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String[] HEADERS = {"Date", "Time", "Description", "Vendor", "Amount"};

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
            String color = t.getAmount().getDoubleValue() > 0 ? GREEN : YELLOW;
            ScreenUtils.printfWithMargins(color + headerRow,
                    t.getDate(),
                    TIME_FMT.format(t.getTime()),
                    t.getDescription(),
                    t.getVendor(),
                    String.format("%.2f", t.getAmount().getDoubleValue()));
        }

        //total
        String total = transactions.stream()
                .map(t -> t.getAmount().getDoubleValue())
                .reduce(0.0, Double::sum)
                .toString();
        ScreenUtils.printlnWithMargins(ANSI_RESET + separator);
        String totalRow = "| %-" + dateW + "s | %-" + timeW + "s | %-" + descW +
                "s | %-" + vendW + "s | %" + amtW + "s |%n";
        ScreenUtils.printfWithMargins(totalRow,
                "Total",
                "",
                "",
                "",
                String.format("%.2f", Double.parseDouble(total)));

        //footer
        ScreenUtils.printlnWithMargins(separator);
        int linesPrinted = transactions.size() + 5;
        ScreenUtils.evenFromTheBottom(linesPrinted);
    }
}
