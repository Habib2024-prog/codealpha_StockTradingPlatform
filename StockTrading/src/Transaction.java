/**
 * This class represents a single transaction, either a buy or a sell.
 */
public class Transaction {

    private String type; // buy or sell
    private Stock stock;
    private int quantity;
    private double price;

    /**
     * Creates a transaction for buying or selling a stock.
     * @param type     "BUY" or "SELL"
     * @param stock    The stock involved in the transaction
     * @param quantity The number of shares
     * @param price    The price per share at the time of transaction
     */
    public Transaction(String type, Stock stock, int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    /**
     * Returns a string version of the transaction for display.
     */
    public String toString() {
        return type + " " + quantity + " of " + stock.getSymbol() + " at $" + price;
    }

    /**
     * Returns a string version of the transaction for saving to a file.
     */
    public String toFileString() {
        return type + "," + stock.getSymbol() + "," + quantity + "," + price;
    }
}
