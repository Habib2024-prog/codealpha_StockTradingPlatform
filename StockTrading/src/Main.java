import java.io.File;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Main class to run the Stock Trading Platform application.
 * It supports user login, registration, and trading operations.
 */
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StockMarket market = new StockMarket();
         market.autoUpdate();  // auto update prices every 30 seconds

        System.out.println("Welcome to stock Trading Platform.");
        System.out.println("Do you want to (1) login or (2) Register?");

        int action = input.nextInt();
        input.nextLine();  // consume leftover newline

        System.out.println("Enter your username?");
        String userName = input.nextLine();

        String userFile = userName + "Data.txt";
        User user = null;

        if (action == 1) {
            // Login process
            File file = new File(userFile);
            if (file.exists()) {
                user = new User(userName, 1000); // default starting balance if needed
                user.loadUser(userFile, market.getMarketStock());
                System.out.println("Logged in as " + userName);
            } else {
                System.out.println("User does not exist, please register first!");
                return;
            }
        } else if (action == 2) {
            // Registration process
            File file = new File(userFile);
            if (file.exists()) {
                System.out.println("User already exists, please login instead!");
                return;
            } else {
                user = new User(userName, 1000); // start with $1000
                System.out.println("Paste/Enter your wallet address: ");
                String wallet = input.nextLine();
                System.out.println("Enter 4-Digit PIN: ");
                String pin = input.nextLine();
                user.setWalletDetails(wallet, pin);
                System.out.println("Account Created.\nWelcome " + userName + "!");
            }
        } else {
            System.out.println("Invalid Option!");
            exit(0);
        }

        // Main trading loop
        while (true) {
            market.updatePrice(); // update prices on every menu display

            showMenu();

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.println("Enter the Stock Symbol: ");
                    String stockSymbol = input.next();
                    System.out.println("Enter the Quantity: ");
                    int quantity = input.nextInt();
                    Stock buyStock = market.getStock(stockSymbol);
                    if (buyStock != null) {
                        user.buy(buyStock, quantity);
                    } else {
                        System.out.println("Invalid Stock Symbol.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the Stock Symbol: ");
                    String sellSymbol = input.next();
                    System.out.println("Enter the Quantity: ");
                    int sellQuantity = input.nextInt();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock != null) {
                        user.sell(sellStock, sellQuantity);
                    } else {
                        System.out.println("Invalid Stock Symbol.");
                    }
                    break;
                case 4:
                    user.showPortfolio(market.getMarketStock());
                    break;
                case 5:
                    user.showHistory();
                    break;
                case 6:
                    System.out.println("Enter deposit amount: ");
                    double amount = input.nextDouble();
                    input.nextLine(); // consume newline
                    user.deposit(amount, input);
                    break;
                case 7:
                    System.out.println("Enter withdraw amount: ");
                    double withdraw = input.nextDouble();
                    input.nextLine(); // consume newline
                    user.withdraw(withdraw, input);
                    break;
                case 0:
                    user.saveUser(userFile);
                    System.out.println("Data saved. Exiting...");
                    System.out.println("GOOD BYE");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // Shows the menu options
    public static void showMenu() {
        System.out.println("<<<<<<< Stock Trading platform >>>>>>>>");
        System.out.println("1. View Market");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. Transaction History");
        System.out.println("6. Deposit fund");
        System.out.println("7. Withdraw fund");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
}
