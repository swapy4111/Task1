import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FAQBot {

    static Map<String, Map<String, Integer>> wordCount = new HashMap<>();
    static Map<String, Integer> intentCount = new HashMap<>();
    static Map<String, String> responses = new HashMap<>();
    static Set<String> vocabulary = new HashSet<>();
    static String[][] trainingData = {
        {"hello", "GREETING"},
        {"hi", "GREETING"},
        {"how are you", "GREETING"},

        {"what is your name", "NAME"},
        {"who are you", "NAME"},

        {"how to buy stock", "BUY"},
        {"how can i buy shares", "BUY"},
        {"purchase stock", "BUY"},

        {"how to sell stock", "SELL"},
        {"sell my shares", "SELL"},

        {"show my portfolio", "PORTFOLIO"},
        {"my portfolio", "PORTFOLIO"},

        {"what is stock price", "PRICE"},
        {"share price", "PRICE"},

        {"help", "HELP"},
        {"what can you do", "HELP"},

        {"bye", "EXIT"},
        {"exit", "EXIT"}
    };

    static {
        responses.put("GREETING", "Hello 😊 How can I help you?");
        responses.put("NAME", "I am your Smart FAQ Bot 🤖");
        responses.put("BUY", "To buy stock, type: buy TCS 5");
        responses.put("SELL", "To sell stock, type: sell TCS 2");
        responses.put("PORTFOLIO", "Your portfolio contains your purchased stocks 📊");
        responses.put("PRICE", "Current demo stock price is ₹1000");
        responses.put("HELP", "You can ask about buying, selling, portfolio, prices, or help.");
        responses.put("EXIT", "Goodbye 👋 Have a nice day!");
        responses.put("UNKNOWN", "Sorry, I didn't understand your question 😕");
    }
    static String clean(String text) {
        return text.toLowerCase().replaceAll("[^a-z ]", "");
    }
    static void train() {
        for (String[] data : trainingData) {
            String sentence = clean(data[0]);
            String intent = data[1];

            intentCount.put(intent, intentCount.getOrDefault(intent, 0) + 1);
            wordCount.putIfAbsent(intent, new HashMap<>());

            for (String word : sentence.split(" ")) {
                vocabulary.add(word);
                Map<String, Integer> wc = wordCount.get(intent);
                wc.put(word, wc.getOrDefault(word, 0) + 1);
            }
        }
    }
    static String predict(String input) {
        input = clean(input);
        String[] words = input.split(" ");

        double maxProb = -999999;
        String bestIntent = "UNKNOWN";

        for (String intent : intentCount.keySet()) {
            double prob = Math.log(intentCount.get(intent));

            for (String word : words) {
                int count = wordCount.get(intent).getOrDefault(word, 0);
                prob += Math.log((count + 1.0) / (vocabulary.size() + intentCount.get(intent)));
            }

            if (prob > maxProb) {
                maxProb = prob;
                bestIntent = intent;
            }
        }
        return bestIntent;
    }
    static String getResponse(String intent) {
        return responses.getOrDefault(intent, responses.get("UNKNOWN"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        train();

        System.out.println("🤖 Smart FAQ Bot Started");
        System.out.println("Ask me questions like:");
        System.out.println("hello, how to buy stock, sell shares, portfolio, help, bye");

        while (true) {
            System.out.print("\nYou: ");
            String input = sc.nextLine();

            String intent = predict(input);
            String reply = getResponse(intent);

            System.out.println("Bot: " + reply);

            if (intent.equals("EXIT")) break;
        }
    }
}