//task2 third task
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PortfolioTracking {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String stockName = "ABC Ltd";
        double stockPrice = 200.0;

        double balance = 10000.0;
        int sharesOwned = 0;

        ArrayList<Double> portfolioHistory = new ArrayList<>();

        int choice;

        do {
            // simulate price change
            stockPrice += random.nextInt(21) - 10; // -10 to +10
            if (stockPrice < 50) stockPrice = 50;

            double portfolioValue = balance + (sharesOwned * stockPrice);
            portfolioHistory.add(portfolioValue);

            System.out.println("\n===== STOCK MARKET =====");
            System.out.println("Stock: " + stockName);
            System.out.println("Current Price: ₹" + stockPrice);

            System.out.println("\n1. Buy Shares");
            System.out.println("2. Sell Shares");
            System.out.println("3. View Portfolio");
            System.out.println("4. View Performance History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter shares to buy: ");
                    int buy = sc.nextInt();
                    double buyCost = buy * stockPrice;

                    if (buyCost <= balance) {
                        sharesOwned += buy;
                        balance -= buyCost;
                        System.out.println(" Shares purchased");
                    } else {
                        System.out.println(" Insufficient balance");
                    }
                    break;

                case 2:
                    System.out.print("Enter shares to sell: ");
                    int sell = sc.nextInt();

                    if (sell <= sharesOwned) {
                        sharesOwned -= sell;
                        balance += sell * stockPrice;
                        System.out.println(" Shares sold");
                    } else {
                        System.out.println(" Not enough shares");
                    }
                    break;

                case 3:
                    System.out.println("\n--- PORTFOLIO ---");
                    System.out.println("Balance: ₹" + balance);
                    System.out.println("Shares Owned: " + sharesOwned);
                    System.out.println("Stock Value: ₹" + (sharesOwned * stockPrice));
                    System.out.println("Total Value: ₹" + portfolioValue);
                    break;

                case 4:
                    System.out.println("\n--- PERFORMANCE OVER TIME ---");
                    for (int i = 0; i < portfolioHistory.size(); i++) {
                        System.out.println("Step " + (i + 1) + ": ₹" + portfolioHistory.get(i));
                    }

                    double profitLoss = portfolioValue - 10000;
                    System.out.println("\nNet Profit/Loss: ₹" + profitLoss);
                    break;

                case 5:
                    System.out.println("Exiting trading system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
