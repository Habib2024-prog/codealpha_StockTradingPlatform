import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents a user in the stock trading platform.
 * It handles balance, wallet info, stock portfolio, and transactions.
 */
public class User {
    private String userName;
    private double balance;
    private Portfolio portfolio;
    private String walletAddress;
    private String PIN;

    /**
     * Creates a new user with name and starting balance.
     */
    public User(String name, double initialBalance) {
        this.balance = initialBalance;
        this.userName = name;
        this.portfolio = new Portfolio();
    }

    /**
     * Sets up the user's wallet address and PIN for transactions.
     */
    public void setWalletDetails(String walletAddress, String PIN) {
        this.walletAddress = walletAddress;
        this.PIN = PIN;
    }

    /**
     * Formats user info for saving to file.
     */
    public String toFileString() {
        return balance + "," + walletAddress + "," + PIN;
    }

    /**
     * Buys stocks if user has enough balance.
     */
    public boolean buy(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (balance >= cost) {
            balance -= cost;
            portfolio.buyStock(stock, quantity);
            return true;
        } else {
            System.out.println("Insufficient Balance.");
            return false;
        }
    }

    /**
     * Sells stocks and adds money to balance.
     */
    public boolean sell(Stock stock, int quantity) {
        portfolio.sellStock(stock, quantity);
        balance += stock.getPrice() * quantity;
        return true;
    }

    /**
     * Saves user info and portfolio to files.
     */
    public void saveUser(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(toFileString());
            portfolio.saveToFile(fileName + "_Portfolio.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads user data and portfolio from files.
     */
    public void loadUser(String fileName, Map<String, Stock> stockMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] parts = reader.readLine().split(",");
            balance = Double.parseDouble(parts[0]);
            walletAddress = parts[1];
            PIN = parts[2];
            portfolio.loadFromFile(fileName + "_Portfolio.txt", stockMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays user's portfolio and net worth.
     */
    public void showPortfolio(Map<String, Stock> stockMap) {
        System.out.println("<<<< Owner Information >>>>");
        System.out.println(userName);
        portfolio.showHolding(stockMap);
        double stockValue = portfolio.totalValue(stockMap);
        System.out.printf("CashBalance: $%.2f\n", balance);
        System.out.printf("StockValue : $%.2f\n", stockValue);
        System.out.printf("NetWorth   : $%.2f\n", (balance + stockValue));
    }

    /**
     * Shows the transaction history of the user.
     */
    public void showHistory() {
        portfolio.showTransaction();
    }

    /**
     * Allows user to deposit money by confirming wallet and PIN.
     */
    public void deposit(double amount, Scanner input) {
        if (walletAddress == null || PIN == null) {
            System.out.println("Wallet not set up.");
            return;
        }
        System.out.println("Paste/Enter your wallet address: ");
        String walletAdd = input.nextLine();
        System.out.println("Enter 4-Digit PIN: ");
        String pin = input.nextLine();

        if (walletAddress.equals(walletAdd) && PIN.equals(pin)) {
            if (amount > 0) {
                balance += amount;
                System.out.printf("Deposited successfully! New balance: %.2f\n", balance);
            } else {
                System.out.println("Invalid amount!");
            }
        } else {
            System.out.println("Invalid PIN or wallet address!");
        }
    }

    /**
     * Allows user to withdraw money after confirming wallet and PIN.
     */
    public void withdraw(double amount, Scanner input) {
        if (walletAddress == null || PIN == null) {
            System.out.println("Wallet not set up.");
            return;
        }
        System.out.println("Paste/Enter your wallet address: ");
        String walletAdd = input.nextLine();
        System.out.println("Enter 4-Digit PIN: ");
        String pin = input.nextLine();

        if (walletAddress.equals(walletAdd) && PIN.equals(pin)) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.printf("Withdrawal successful! New balance: %.2f\n", balance);
            } else {
                System.out.println("Invalid amount!");
            }
        } else {
            System.out.println("Invalid PIN or wallet address!");
        }
    }
}
