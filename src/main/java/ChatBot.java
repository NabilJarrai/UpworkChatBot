/**
 * @author Nabil Jarrai
 * @since 14/07/2023
 */

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBotEngine chatBotEngine = new ChatBotEngine();

        while (true) {
            System.out.print("User: ");
            String userMessage = scanner.nextLine();

            if (userMessage.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            String chatbotResponse = chatBotEngine.getChatBotResponse(userMessage);
            System.out.println("Chatbot: " + chatbotResponse);
        }

        scanner.close();
    }
}
