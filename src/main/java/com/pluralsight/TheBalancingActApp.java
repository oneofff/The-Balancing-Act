package com.pluralsight;

import com.pluralsight.repository.CsvTransactionRepository;
import com.pluralsight.service.BalancingAppCoordinator;

public class TheBalancingActApp {

    public static void main(String[] args) {
        addShutdownHook();
        BalancingAppCoordinator.start();
    }

   // This method is used to close the resources when the application is terminated
    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Shutdown hook triggered: Closing resources...");
            try{
                CsvTransactionRepository.getInstance().close();
            }catch (Exception e)  {
                System.err.println("Error closing CsvTransactionRepository: " + e.getMessage());
            }
        }));
    }


}
