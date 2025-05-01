# Accounting Ledger Application (The Balancing Act)

This is a console-based accounting ledger application written in Java. It allows users to track income (deposits) and expenses (payments) by recording transactions, which are persistently stored in a CSV file.

## Project Description

"The Balancing Act" provides a simple, menu-driven command-line interface for managing personal finances. Users can add new transactions, view lists of existing transactions filtered by type (all, deposits, payments), and generate various financial reports based on date ranges (e.g., Month-to-Date, Year-to-Date, Custom Search). All transaction data is read from and saved to a local CSV file (`data/transactions.csv` by default), making it easy to view or manipulate the data outside the application if needed.

## Features

* **Transaction Management:** Add deposits and payments with date, time, description, vendor, and amount details.
* **Data Persistence:** Transactions are saved to a CSV file (`data/transactions.csv`) for persistence between application runs[cite: 1].
* **Ledger Viewing:** Display lists of all transactions, only deposits, or only payments.
* **Reporting:** Generate reports for specific periods:
    * Month-To-Date
    * Previous Month
    * Year-To-Date
    * Previous Year
* **Custom Search:** Filter transactions based on start/end date, description, vendor, and/or amount.
* **Console Interface:** User-friendly menu system for navigation and data entry.
* **Formatted Output:** Transactions and reports are displayed in a clean, tabular format in the console.

## How it Works

The application uses a layered structure:

1.  **UI Layer (`com.pluralsight.ui`):** Handles console input and output, displaying menus (`HomeScreen`, `LedgerScreen`, `ReportScreen`), prompting for user input (`AddTransactionScreen`, `CustomSearchScreen`), and displaying formatted data (`DisplayTransactionScreen`, `ScreenUtils`, `TransactionPrinter`).
2.  **Service Layer (`com.pluralsight.service`):** Coordinates application flow (`BalancingAppCoordinator`) and orchestrates actions like adding transactions or performing searches (`TransactionService`).
3.  **Repository Layer (`com.pluralsight.repository`):** Manages data access. `CsvTransactionRepository` implements the `TransactionRepository` interface, handling the reading and writing of `Transaction` objects to/from the configured CSV file.
4.  **Model Layer (`com.pluralsight.model`):** Defines the data structure for a `Transaction`.
5.  **Configuration (`com.pluralsight.config`):** `AppConfig` loads settings like the CSV file path from `application.properties`.
6.  **Utilities (`com.pluralsight.utils`):** Provide helper functions for console I/O, file handling, data type management (`BankBigDecimal`), and screen formatting.

Data is stored in a pipe-delimited CSV file (`|`), typically located at `data/transactions.csv`[cite: 1].

## Application Screens (Console Output Examples)

*(Since this is a console application, "screens" refer to the text output in the terminal)*

**1. Welcome Screen:**![img.png](screenshots/img.png) The initial screen displayed upon starting the application.
 **2. Home Menu:** ![img_1.png](screenshots/img_1.png) The main navigation menu.
 **3. Ledger Display:** ![img_2.png](screenshots/img_2.png) The Ledger menu.
 **4. Report Menu:** ![img_3.png](screenshots/img_3.png) Provides options for generating reports.
## Interesting Code Snippet: BalancingAppCoordinator
```java
public class BalancingAppCoordinator {

    public static void start() {
        WelcomeScreen.print();
        homeScreenFlow();
    }

    public static void homeScreenFlow() {
        while (true) {
            HomeScreen.print();
            int option = ScreenUtils.askForMenuOptionsInput(HomeScreen.amountOfOptions());
            performAction(option, HomeScreen.MenuOption.class);
        }
    }

    public static void ledgerScreenFlow() {
        while (true) {
            LedgerScreen.print();
            int option = ScreenUtils.askForMenuOptionsInput(LedgerScreen.amountOfOptions());
            performAction(option, LedgerScreen.MenuOption.class);
        }
    }

    public static void reportScreenFlow() {
        while (true) {
            ReportScreen.print();
            int option = ScreenUtils.askForMenuOptionsInput(ReportScreen.amountOfOptions());
            performAction(option, ReportScreen.MenuOption.class);
        }
    }

    public static <T extends Enum<T> & MenuEntry> void performAction(int option, Class<T> menu) {
        T menuOption = Arrays.stream(menu.getEnumConstants())
                .filter(entry -> entry.getValue() == option)
                .findFirst()
                .orElse(null);
        if (menuOption != null) {
            ScreenUtils.cls();
            menuOption.getAction().run();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    public static void exit() {
        closeResources();
        SeeYouScreen.print();
        System.exit(0);
    }

    private static void closeResources() {
        try {
            ConsoleStringReader.close();
            CsvTransactionRepository.getInstance().close();
        } catch (Exception e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
```