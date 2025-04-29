package com.pluralsight.service;

import com.pluralsight.ui.SeeYouScreen;

public class TransactionService {
    public static void exit() {
        System.out.println("Exiting the application...");
        SeeYouScreen.print();
        System.exit(0);
    }

    public static void makePayment() {
        System.out.println("Make payment");
        // Implement the logic for making a payment
    }

    public static void addDeposit(){
        System.out.println("Add deposit");
        // Implement the logic for adding a deposit
    }
}
