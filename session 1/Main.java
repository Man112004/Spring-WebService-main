package com.example;

import okhttp3.*;

public class Main {

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
            System.out.println(response.body().string());
        }
    }
}