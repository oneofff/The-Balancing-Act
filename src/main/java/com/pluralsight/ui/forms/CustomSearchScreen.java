package com.pluralsight.ui.forms;

import com.pluralsight.model.Transaction;
import com.pluralsight.service.TransactionService;
import com.pluralsight.ui.dto.TransactionSearchCriteria;
import com.pluralsight.utils.DataTypes.BankBigDecimal;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class CustomSearchScreen {
    public static void customSearch() {
        TransactionSearchCriteria criteria = getTransactionSearchCriteria();

        List<Transaction> transactions = TransactionService.customSearch(criteria);

        ScreenUtils.printTransactionsReverse(transactions, "Custom search results");
    }

    private static TransactionSearchCriteria getTransactionSearchCriteria() {
        TransactionSearchCriteria.TransactionSearchCriteriaBuilder builder = TransactionSearchCriteria.builder();

        ScreenUtils.printOnCenterOfTheScreen("Custom search:");
        ScreenUtils.printlnWithMargins("Enter the start date (yyyy-MM-dd): ");
        builder.startDate(ConsoleStringReader.getDateWithMargin());
        ScreenUtils.printlnWithMargins("Enter the end date (yyyy-MM-dd): ");
        builder.endDate(ConsoleStringReader.getDateWithMargin());
        ScreenUtils.printlnWithMargins("Enter the description: ");
        builder.description(ConsoleStringReader.getStringWithMargin());
        ScreenUtils.printlnWithMargins("Enter the vendor: ");
        builder.vendor(ConsoleStringReader.getStringWithMargin());
        ScreenUtils.printlnWithMargins("Enter the amount: ");
        String amount = ConsoleStringReader.getStringWithMargin();
        if (!amount.isEmpty()) {
            builder.amount(new BankBigDecimal(amount));
        }

        ScreenUtils.cls();
        return builder.build();
    }
}
