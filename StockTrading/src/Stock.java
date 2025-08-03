/**
 * This class represents a stock with a name, symbol, and current price.
 */
public class Stock {

    private String name;
    private String symbol;
    private double price;

    /**
     * Constructor to initialize a stock.
     * @param name   The company name.
     * @param symbol The stock symbol (e.g., AAPL).
     * @param price  The current stock price.
     */
    public Stock(String name, String symbol, double price) {
        this.name = name;
        this.price = price;
        this.symbol = symbol;
    }

    /**
     * Returns the name of the stock.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current price of the stock.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the stock symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Updates the price of the stock.
     * @param newPrice The new price to set.
     */
    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * Returns the stock as a formatted string.
     */
    @Override
    public String toString() {
        return symbol + " - " + name + ": $" + price;
    }
}
