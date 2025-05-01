package com.pluralsight.ui.dto;

import com.pluralsight.model.Transaction;
import com.pluralsight.utils.DataTypes.BankBigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.function.Predicate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSearchCriteria {
    private String description;
    private String vendor;
    private BankBigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;


    public Predicate<Transaction> toPredicate() {
        return t ->
                (startDate == null || !t.getDate().isBefore(startDate)) &&
                        (endDate == null  || !t.getDate().isAfter(endDate)) &&
                        (description == null || description.isEmpty() || t.getDescription()
                                .equalsIgnoreCase(description)) &&
                        (vendor == null|| vendor.isEmpty() || t.getVendor()
                                .equalsIgnoreCase(vendor)) &&
                        (amount == null || t.getAmount()
                                .compareTo(amount) == 0);
    }
}
