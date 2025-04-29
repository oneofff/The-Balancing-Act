package com.pluralsight.ui;

import com.pluralsight.utils.ScreenUtils;

import java.util.List;

public class SeeYouScreen {
    public static void print() {

        try {
            ScreenUtils.printBox(List.of("See you next time!",
                    "Thank you for using The Balancing Act App.",
                    "Goodbye!"));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
