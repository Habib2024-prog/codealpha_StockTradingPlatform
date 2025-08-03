import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a user's portfolio.
 * It keeps track of stock holdings and all buy/sell transactions.
 */
public class Portfolio {

    private Map<String, Integer> holding = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Buys a given quantity of a stock and adds it to holdings and transaction history.
     */
    public void buyStock(Stock stock, int quantity) {
        holding.put(stock.getSymbol(), holding.getOrDefault(stock.getSymbol(), 0) + quantity);
        transactions.add(new Transaction("BUY", stock, quantity, stock.getPrice()));
        System.out.println("(" + quantity + stock.getSymbol() + ") Stock successfully bought at: $" + stock.getPrice() + " per stock.");
    }

    /**
     * Sells a given quantity of a stock if available in holdings.
     */
    public void sellStock(Stock stock, int quantity) {
        int current = holding.getOrDefault(stock.getSymbol(), 0);
        if (current >= quantity) {
            holding.put(stock.getSymbol(), current - quantity);
            transactions.add(new Transaction("SELL", stock, quantity, stock.getPrice()));
            System.out.println("(" + quantity + stock.getSymbol() + ") Stock successfully sold at: $" + stock.getPrice() + " per stock");
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    /**
     * Displays all current stock holdings with their values.
     */
    public void showHolding(Map<String, Stock> stockMap) {
        System.out.println("Your Portfolio:");
        for (String symbol : holding.keySet()) {
            int quantity = holding.get(symbol);
            Stock stock = stockMap.get(symbol);
            double value = stock.getPrice() * quantity;
            System.out.println(symbol + " - Quantity: " + quantity + ", Value: $" + value);
        }
    }

    /**
     * Displays the list of all buy/sell transactions.
     */
    public void showTransaction() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    /**
     * Calculates the total current value of all holdings.
     */
    public double totalValue(Map<String, Stock> stockMap) {
        double total = 0;
        for (String symbol : holding.keySet()) {
            int quantity = holding.get(symbol);
            Stock stock = stockMap.get(symbol);
            if (stock != null) {
                total += stock.getPrice() * quantity;
            }
        }
        return total;
    }

    /**
     * Saves the current holdings and transaction history to a file.
     */
    public void saveToFile(String fileName) {
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry : holding.entrySet()) {
                writer.println("Holding," + entry.getKey() + "," + entry.getValue());
            }
            for (Transaction t : transactions) {
                writer.println("Transaction," + t.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads holdings and transactions from a file.
     */
    public void loadFromFile(String fileName, Map<String, Stock> stockMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            holding.clear();
            transactions.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Holding")) {
                    holding.put(parts[1], Integer.parseInt(parts[2]));
                } else if (parts[0].equals("Transaction")) {
                    String type = parts[1];
                    String symbol = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    Stock stock = stockMap.get(symbol);
                    if (stock != null) {
                        transactions.add(new Transaction(type, stock, quantity, price));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
