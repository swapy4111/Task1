// task2 first task
import java.util.Scanner;

public class StockTradingSimulation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000.0;   // starting money
        int sharesOwned = 0;
        double stockPrice = 250.0;  // fixed stock price

        int choice;

        do {
            System.out.println("\n--- Stock Trading Menu ---");
            System.out.println("1. View Stock Price");
            System.out.println("2. Buy Shares");
            System.out.println("3. Sell Shares");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Stock Price: ₹" + stockPrice);
                    break;

                case 2:
                    System.out.print("Enter number of shares to buy: ");
                    int buy = sc.nextInt();
                    double buyCost = buy * stockPrice;

                    if (buyCost <= balance) {
                        sharesOwned += buy;
                        balance -= buyCost;
                        System.out.println("Shares bought successfully!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;

                case 3:
                    System.out.print("Enter number of shares to sell: ");
                    int sell = sc.nextInt();

                    if (sell <= sharesOwned) {
                        sharesOwned -= sell;
                        balance += sell * stockPrice;
                        System.out.println("Shares sold successfully!");
                    } else {
                        System.out.println("Not enough shares to sell!");
                    }
                    break;

                case 4:
                    System.out.println("Balance: ₹" + balance);
                    System.out.println("Shares Owned: " + sharesOwned);
                    System.out.println("Portfolio Value: ₹" + (sharesOwned * stockPrice));
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
