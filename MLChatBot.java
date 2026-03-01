import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MLChatBot {

    static Map<String, Map<String, Integer>> wordCount = new HashMap<>();
    static Map<String, Integer> intentCount = new HashMap<>();
    static Set<String> vocabulary = new HashSet<>();

    // Training data
    static String[][] trainingData = {
        {"hello", "GREETING"},
        {"hi", "GREETING"},
        {"hey", "GREETING"},
        {"buy stock", "BUY"},
        {"purchase shares", "BUY"},
        {"sell stock", "SELL"},
        {"sell shares", "SELL"},
        {"show portfolio", "PORTFOLIO"},
        {"my portfolio", "PORTFOLIO"},
        {"stock price", "PRICE"},
        {"share price", "PRICE"},
        {"bye", "EXIT"},
        {"exit", "EXIT"}
    };

    // Train model
    static void train(){
        for(String[] data : trainingData){
            String sentence = data[0];
            String intent = data[1];

            intentCount.put(intent, intentCount.getOrDefault(intent,0)+1);
            wordCount.putIfAbsent(intent, new HashMap<>());

            for(String word : sentence.split(" ")){
                vocabulary.add(word);
                Map<String,Integer> wc = wordCount.get(intent);
                wc.put(word, wc.getOrDefault(word,0)+1);
            }
        }
    }

    // Predict intent (ML logic)
    static String predict(String input){
        String[] words = input.toLowerCase().split(" ");
        double maxProb = -1;
        String bestIntent = "UNKNOWN";

        for(String intent : intentCount.keySet()){
            double prob = Math.log(intentCount.get(intent));

            for(String word : words){
                int count = wordCount.get(intent).getOrDefault(word,0);
                prob += Math.log((count + 1.0) / (vocabulary.size() + intentCount.get(intent)));
            }

            if(prob > maxProb){
                maxProb = prob;
                bestIntent = intent;
            }
        }
        return bestIntent;
    }

    static String getResponse(String intent){
        switch(intent){
            case "GREETING": return "Hello! 😊";
            case "BUY": return "Buying stock...";
            case "SELL": return "Selling stock...";
            case "PORTFOLIO": return "Here is your portfolio 📊";
            case "PRICE": return "Stock price is ₹1000";
            case "EXIT": return "Goodbye 👋";
            default: return "I don't understand 🤖";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        train(); // Train ML model

        System.out.println("🤖 ML NLP Bot Started");

        while(true){
            System.out.print("\nYou: ");
            String input = sc.nextLine();

            String intent = predict(input);
            String response = getResponse(intent);

            System.out.println("Bot: " + response);
        }
    }
}