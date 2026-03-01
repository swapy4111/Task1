// task2 second task
import java.util.Random;
import java.util.Scanner;

public class StockMarketSimulation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String stockName = "sw pvt Ltd";
        double stockPrice = 200.0;

        double balance = 10000.0;
        int sharesOwned = 0;

        int choice;

        do {
            // simulate market price change
            stockPrice += random.nextInt(21) - 10; // -10 to +10
            if (stockPrice < 50) {
                stockPrice = 50; // minimum price
            }

            System.out.println("\n===== STOCK MARKET =====");
            System.out.println("Stock: " + stockName);
            System.out.println("Current Price: ₹" + stockPrice);

            System.out.println("\n1. Buy Shares");
            System.out.println("2. Sell Shares");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of shares to buy: ");
                    int buyQty = sc.nextInt();
                    double buyAmount = buyQty * stockPrice;

                    if (buyAmount <= balance) {
                        sharesOwned += buyQty;
                        balance -= buyAmount;
                        System.out.println("✅ Shares bought successfully!");
                    } else {
                        System.out.println("❌ Insufficient balance!");
                    }
                    break;

                case 2:
                    System.out.print("Enter number of shares to sell: ");
                    int sellQty = sc.nextInt();

                    if (sellQty <= sharesOwned) {
                        sharesOwned -= sellQty;
                        balance += sellQty * stockPrice;
                        System.out.println("✅ Shares sold successfully!");
                    } else {
                        System.out.println("❌ Not enough shares!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- PORTFOLIO ---");
                    System.out.println("Balance: ₹" + balance);
                    System.out.println("Shares Owned: " + sharesOwned);
                    System.out.println("Market Value: ₹" + (sharesOwned * stockPrice));
                    break;

                case 4:
                    System.out.println("Exiting market... 📉");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
