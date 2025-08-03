import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class simulates a stock market that holds multiple stocks.
 * It can update prices randomly and display the current market data.
 */
public class StockMarket {
    private Map<String, Stock> marketStock = new HashMap<>();

    /**
     * Initializes the market with some sample stocks.
     */
    public StockMarket() {
        marketStock.put("APPL", new Stock("Apple", "APPL", 150));
        marketStock.put("AMZ", new Stock("Amazon", "AMZ", 200));
        marketStock.put("GOOGLE", new Stock("Google", "GOOGLE", 500));
        marketStock.put("MS", new Stock("MicroSoft", "MS", 600));
    }

    /**
     * Randomly updates prices of all stocks in the market.
     * Price can change by +/- up to 5 dollars.
     */
    public void updatePrice() {
        Random random = new Random();
        for (Stock stock : marketStock.values()) {
            // Calculate price change between -5 and +5
            double change = (random.nextDouble() - 0.5) * 10;
            double newPrice = Math.max(1, stock.getPrice() + change); // Price can't go below 1
            stock.updatePrice(newPrice);
        }
    }

    /**
     * Automatically updates stock prices every 20 seconds in a background thread.
     */
    public void autoUpdate() {
        Thread updater = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30000); // Wait for 30 seconds
                    updatePrice();
                    System.out.println("{Market auto-updated}");
                } catch (InterruptedException e) {
                    break; // Stop the thread if interrupted
                }
            }
        });
        updater.setDaemon(true); // Set as daemon thread so it won't block program exit
        updater.start();
    }

    /**
     * Prints the current market data to console.
     */
    public void displayMarket() {
        System.out.println("Market data:");
        for (Stock stock : marketStock.values()) {
            System.out.println(stock);
        }
    }

    /**
     * Returns the full map of stocks in the market.
     */
    public Map<String, Stock> getMarketStock() {
        return marketStock;
    }

    /**
     * Returns a stock by its symbol.
     */
    public Stock getStock(String symbol) {
        return marketStock.get(symbol);
    }
}
