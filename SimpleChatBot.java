import java.util.Date;
import java.util.Scanner;

public class SimpleChatBot {

    static String getReply(String msg) {
        msg = msg.toLowerCase();

        if(msg.contains("hello") || msg.contains("hi"))
            return "Hello! 😊 How can I help you?";

        if(msg.contains("your name"))
            return "I am JavaBot 🤖";

        if(msg.contains("how are you"))
            return "I'm doing great! Thanks for asking 😄";

        if(msg.contains("help"))
            return "You can ask me about: greetings, time, date, java, bye";

        if(msg.contains("time"))
            return "Current time: " + new Date().toString();

        if(msg.contains("date"))
            return "Today's date: " + new Date().toString();

        if(msg.contains("java"))
            return "Java is a powerful programming language ☕";

        if(msg.contains("bye") || msg.contains("exit"))
            return "Goodbye 👋 Have a nice day!";

        return "Sorry, I didn't understand 😕 Try: hello, help, time, java, bye";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("🤖 Java ChatBot Started");
        System.out.println("Type: hello, help, time, date, java, bye");

        while(true) {
            System.out.print("\nYou: ");
            String userMsg = sc.nextLine();

            String reply = getReply(userMsg);
            System.out.println("Bot: " + reply);

            if(userMsg.toLowerCase().contains("bye") || userMsg.toLowerCase().contains("exit"))
                break;
        }
    }
}
