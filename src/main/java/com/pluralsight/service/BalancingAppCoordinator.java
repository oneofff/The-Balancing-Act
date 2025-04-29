package com.pluralsight.service;

import com.pluralsight.ui.HomeScreen;
import com.pluralsight.ui.WelcomeScreen;

public class BalancingAppCoordinator {

    public static void start() {
        WelcomeScreen.print();
        homeScreenFlow();
    }

    private static void homeScreenFlow() {
        while (true) {
        HomeScreen.print();
        int option = HomeScreen.askForInput();
        HomeScreen.performAction(option);
        }
    }
}
