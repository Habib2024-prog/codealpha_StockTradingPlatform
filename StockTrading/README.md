# 📈 Stock Trading Platform

A simple console-based Stock Trading Simulation developed in Java using Object-Oriented Programming (OOP) principles.

## ✅ Features

-  **Market Simulation**: Live stock price updates (simulated).
-  **Buy/Sell Stocks**: Users can buy or sell based on current market prices.
-  **Transaction History**: Keeps track of all trading transactions.
-  **Portfolio Management**: Displays user's current stock holdings and value.
-  **User Accounts**:
    - Register/Login system with persistent storage.
    - Wallet Address and PIN for secure deposits/withdrawals.
-  **Deposit & Withdraw**: Manage account balance securely.
-  **File I/O**: Portfolio and transaction data is saved and loaded from text files.

---

## 🛠️ Project Structure
### StockTrading/
-  ├── .idea/                       # IntelliJ project config
-  ├── out/                         # Compiler output
-  ├── src/**                         # Source code
-  │   ├── Main.java                # Entry point
-  │   ├── User.java                # User account, buy/sell, deposit/withdraw
-  │   ├── Portfolio.java           # Portfolio management (if added separately)
-  │   ├── Stock.java               # Stock representation (name, symbol, price)
-  │   ├── StockMarket.java         # Simulates market and price updates
-  │   ├── Transaction.java         # Handles buy/sell records
-  ├── HabibData.txt                # User data file
-  ├── HabibData.txt_Portfolio.txt # User portfolio file
-  ├── MohammadData.txt            # Another user data file
-  ├── MohammadData.txt_Portfolio.txt
-  ├── .gitignore                   # Git ignore settings
-  ├── README.md                    # Project documentation
-  ├── StockTrading.iml  
- end
##   🧑‍💻 How to Run
- 1. clone the Repository
- 2. git clone https://github.com/yourusername/StockTrading.git
     cd StockTrading
- 3. Open with IntelliJ IDEA

- 4. Run the Main.java
     This file launches the interactive menu-driven console app.
## 🔐 User Instructions
-   **Register/Login**: You must register with a wallet address and PIN.
       - On later runs, the same credentials are used to log in.

-  **Deposit/Withdraw**: Wallet address and PIN must match. 
     - Deposit increases your balance. 
     - Withdraw deducts from your balance (if sufficient funds).

-  **Buy Stocks**: Stocks have live prices. 
     - Select a stock symbol and quantity. 
     - Cost is deducted from your balance.

-  **Sell Stocks**: You must own the stock in your portfolio. 
      - You earn back the current market price * quantity.

- **View Portfolio**: Shows all owned stocks with quantity. 
     - Transaction history also available.
## 📝 Data Storage Format
- UsernameData.txt : balance,wallerAddress,PIN
- UsernameData.txt_Portfolio.txt: 
   - Holding,Stock symbol,Quantity
   - Transaction,Type,Stock symbol,Quantity,price

## 📦 Dependencies
- 1. Standard Java SE (no external libraries)
- 2. JDK 8+ recommended
- 3. IntelliJ IDEA (for ease of development)
## 🧪 Future Improvements
- GUI version (JavaFX or Swing)
- File export of full transaction logs
- Admin dashboard for controlling market
- More advanced stock fluctuation model
 ##  👨‍🎓 Author
- **Habibullah Khaliqyar**
- Bs Data science, NUST University, Islamabad 







