import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// Stock class
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

// Portfolio class
class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();

    void addStock(String symbol, int qty) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
    }

    void show() {
        System.out.println("\n📊 Portfolio:");
        for(String s : holdings.keySet()) {
            System.out.println(s + " = " + holdings.get(s) + " shares");
        }
    }
}

// User class
class User {
    String name;
    double balance;
    Portfolio portfolio = new Portfolio();

    User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}

// File Storage
class FileStore {
    static String FILE = "data.txt";

    static void save(User user) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            pw.println(user.name + "|" + user.balance);
            for(String s : user.portfolio.holdings.keySet()) {
                pw.println(s + "|" + user.portfolio.holdings.get(s));
            }
        } catch(Exception e) {
            System.out.println("Save error");
        }
    }

    static User load() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line = br.readLine();
            if(line == null) return null;

            String[] u = line.split("\\|");
            User user = new User(u[0], Double.parseDouble(u[1]));

            while((line = br.readLine()) != null) {
                String[] s = line.split("\\|");
                user.portfolio.addStock(s[0], Integer.parseInt(s[1]));
            }
            return user;
        } catch(Exception e) {
            return null;
        }
    }
}

// Main App
public class SimpleStockApp {
    public static void main(String[] args) {

        // Load data if exists
        User user = FileStore.load();
        if(user == null) {
            user = new User("Swapnil", 100000);
        }

        // Market stock
        Stock tcs = new Stock("TCS", 3800);

        // Buy stock
        int qty = 5;
        double cost = tcs.price * qty;

        if(user.balance >= cost) {
            user.balance -= cost;
            user.portfolio.addStock(tcs.symbol, qty);
            System.out.println("✅ Bought " + qty + " shares of " + tcs.symbol);
        }

        // Show data
        System.out.println("💰 Balance: ₹" + user.balance);
        user.portfolio.show();

        // Save before exit
        FileStore.save(user);
        System.out.println("\n💾 Data Saved to file");
    }
}