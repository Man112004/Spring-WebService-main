package com.example;

import java.util.List;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args) {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("Please set OPENAI_API_KEY.");
            return;
        }

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4.1-mini")
                .build();

        List<String> questions = List.of(
                "How to use Paytm wallet?",
                "How to book a movie on BookMyShow?",
                "How do I get a refund on Flipkart?");

        for (String question : questions) {

            String answer = model.chat(question);

            System.out.println("\nQuestion: " + question);
            System.out.println("Answer: " + answer);
        }
    }
}