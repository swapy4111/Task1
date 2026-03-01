import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SimpleNLPApp {
    static String preprocess(String text) {
        return text.toLowerCase().replaceAll("[^a-zA-Z ]", "");
    }

    static List<String> tokenize(String text) {
        return Arrays.asList(text.split("\\s+"));
    }
    static String detectIntent(List<String> tokens) {

        if(tokens.contains("hello") || tokens.contains("hi"))
            return "GREETING";

        if(tokens.contains("buy") || tokens.contains("purchase"))
            return "BUY_STOCK";

        if(tokens.contains("sell"))
            return "SELL_STOCK";

        if(tokens.contains("price"))
            return "STOCK_PRICE";

        if(tokens.contains("portfolio"))
            return "SHOW_PORTFOLIO";

        if(tokens.contains("bye") || tokens.contains("exit"))
            return "EXIT";

        return "UNKNOWN";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<String, Integer> portfolio = new HashMap<>();
        double balance = 100000;

        System.out.println("🤖 NLP Trading Bot Started!");
        System.out.println("Type: hello, buy TCS 5, sell TCS 2, portfolio, price, exit");

        while(true) {
            System.out.print("\nYou: ");
            String input = sc.nextLine();

            String cleanText = preprocess(input);
            List<String> tokens = tokenize(cleanText);

            String intent = detectIntent(tokens);

            switch(intent) {

                case "GREETING":
                    System.out.println("Bot: Hello! How can I help you? 😊");
                    break;

                case "BUY_STOCK":
                    try {
                        String stock = tokens.get(1).toUpperCase();
                        int qty = Integer.parseInt(tokens.get(2));
                        double price = 1000; // demo price
                        double cost = price * qty;

                        if(balance >= cost) {
                            balance -= cost;
                            portfolio.put(stock, portfolio.getOrDefault(stock, 0) + qty);
                            System.out.println("Bot: Bought " + qty + " shares of " + stock);
                        } else {
                            System.out.println("Bot: Insufficient balance");
                        }
                    } catch(Exception e) {
                        System.out.println("Bot: Use format → buy TCS 5");
                    }
                    break;

                case "SELL_STOCK":
                    try {
                        String stock = tokens.get(1).toUpperCase();
                        int qty = Integer.parseInt(tokens.get(2));
                        double price = 1000;

                        if(portfolio.getOrDefault(stock, 0) >= qty) {
                            portfolio.put(stock, portfolio.get(stock) - qty);
                            balance += price * qty;
                            System.out.println("Bot: Sold " + qty + " shares of " + stock);
                        } else {
                            System.out.println("Bot: Not enough shares");
                        }
                    } catch(Exception e) {
                        System.out.println("Bot: Use format → sell TCS 2");
                    }
                    break;

                case "SHOW_PORTFOLIO":
                    System.out.println("Bot: Your Portfolio 📊");
                    for(String s : portfolio.keySet()) {
                        System.out.println(s + " = " + portfolio.get(s));
                    }
                    System.out.println("Balance = ₹" + balance);
                    break;

                case "STOCK_PRICE":
                    System.out.println("Bot: Demo Price = ₹1000 per share");
                    break;

                case "EXIT":
                    System.out.println("Bot: Goodbye 👋");
                    return;

                default:
                    System.out.println("Bot: I didn't understand. Try: buy, sell, portfolio, price");
            }
        }
    }
}