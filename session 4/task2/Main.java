package com.example;

import dev.langchain4j.model.input.PromptTemplate;

public class Main {
    public static void main(String[] args) {

        PromptTemplate template = PromptTemplate.from(
                "Suggest a Zomato-style restaurant for this food craving: {{craving}}. " +
                        "Include restaurant name, food items, price range, and rating.");

        String prompt = template.apply(
                java.util.Map.of("craving", "I want something spicy")).text();

        System.out.println("Generated Prompt:");
        System.out.println(prompt);
    }
}