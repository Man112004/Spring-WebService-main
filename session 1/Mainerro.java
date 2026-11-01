package com.example;

import okhttp3.*;

public class Mainerro {

    public static void main(String[] args) throws Exception {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("Please set OPENAI_API_KEY.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        String json = """
                {
                  "model": "gpt-4.1-mini",
                  "input": "Suggest 3 creative Instagram captions for a food photo."
                }
                """;

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/responses")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String result = response.body() != null
                    ? response.body().string()
                    : "";

            if (response.isSuccessful()) {
                System.out.println("API Response:");
                System.out.println(result);
            } else {
                System.out.println("API Error!");
                System.out.println("Status Code: " + response.code());

                if (response.code() == 401) {
                    System.out.println("Invalid API key. Check your API key.");
                } else if (response.code() == 429) {
                    System.out.println("Rate limit exceeded or quota unavailable.");
                } else if (response.code() == 400) {
                    System.out.println("Bad request. Check your input.");
                } else {
                    System.out.println("Something went wrong.");
                }

                System.out.println("Error Details: " + result);
            }
        } catch (java.io.IOException e) {
            System.out.println("Network error: " + e.getMessage());
        }
    }

}