// OOP-based Stock Trading System in Java

import java.util.*;

// ---------- Stock Class ----------
class Stock {
    private String symbol;
    private String companyName;
    private double price;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " - " + companyName + " | Price: ₹" + price;
    }
}

// ---------- User Class ----------
class User {
    private int userId;
    private String name;
    private double balance;
    private Map<String, Integer> portfolio = new HashMap<>();

    public User(int userId, String name, double balance) {
        this.userId = userId;
        this.name = name;
        this.balance = balance;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void addBalance(double amount) {
        balance += amount;
    }

    public boolean deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void buyStock(String symbol, int qty) {
        portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + qty);
    }

    public boolean sellStock(String symbol, int qty) {
        if (portfolio.containsKey(symbol) && portfolio.get(symbol) >= qty) {
            portfolio.put(symbol, portfolio.get(symbol) - qty);
            if (portfolio.get(symbol) == 0)
                portfolio.remove(symbol);
            return true;
        }
        return false;
    }

    public void viewPortfolio() {
        System.out.println("\nPortfolio of " + name + ":");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (String s : portfolio.keySet()) {
                System.out.println(s + " -> " + portfolio.get(s) + " shares");
            }
        }
    }
}

// ---------- Transaction Class ----------
class Transaction {
    private static int counter = 1;
    private int transactionId;
    private int userId;
    private String stockSymbol;
    private int quantity;
    private String type; // BUY / SELL
    private Date date;

    public Transaction(int userId, String stockSymbol, int quantity, String type) {
        this.transactionId = counter++;
        this.userId = userId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.type = type;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "TXN#" + transactionId + " | User: " + userId + " | " + type + " " + quantity + " " + stockSymbol + " | " + date;
    }
}

// ---------- Market Class ----------
class Market {
    private Map<String, Stock> stocks = new HashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void displayStocks() {
        System.out.println("\nAvailable Stocks:");
        for (Stock s : stocks.values()) {
            System.out.println(s);
        }
    }
}

// ---------- Trading System (Main Class) ----------
public class TradingSystem {
    static Market market = new Market();
    static List<User> users = new ArrayList<>();
    static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Data
        market.addStock(new Stock("TCS", "Tata Consultancy Services", 3850));
        market.addStock(new Stock("INFY", "Infosys", 1600));
        market.addStock(new Stock("RELI", "Reliance", 2500));

        users.add(new User(1, "Swapnil", 100000));

        while (true) {
            System.out.println("\n===== STOCK TRADING SYSTEM =====");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            User user = users.get(0); // single user demo

            switch (ch) {
                case 1:
                    market.displayStocks();
                    break;

                case 2:
                    System.out.print("Enter Stock Symbol: ");
                    String buySym = sc.next();
                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();

                    Stock buyStock = market.getStock(buySym);
                    if (buyStock != null) {
                        double cost = buyQty * buyStock.getPrice();
                        if (user.deductBalance(cost)) {
                            user.buyStock(buySym, buyQty);
                            transactions.add(new Transaction(user.getUserId(), buySym, buyQty, "BUY"));
                            System.out.println("Stock Purchased Successfully!");
                        } else {
                            System.out.println("Insufficient Balance!");
                        }
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSym = sc.next();
                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();

                    Stock sellStock = market.getStock(sellSym);
                    if (sellStock != null) {
                        if (user.sellStock(sellSym, sellQty)) {
                            double amount = sellQty * sellStock.getPrice();
                            user.addBalance(amount);
                            transactions.add(new Transaction(user.getUserId(), sellSym, sellQty, "SELL"));
                            System.out.println("Stock Sold Successfully!");
                        } else {
                            System.out.println("Not enough shares to sell!");
                        }
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 4:
                    user.viewPortfolio();
                    System.out.println("Balance: ₹" + user.getBalance());
                    break;

                case 5:
                    System.out.println("\nTransactions:");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;

                case 6:
                    System.out.println("Exiting System...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
