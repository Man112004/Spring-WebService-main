package com.example;

import okhttp3.*;

public class HuggingFaceDemo {

    public static void main(String[] args) throws Exception {

        OkHttpClient client = new OkHttpClient();

        String json = """
                {
                  "inputs": "Java programming is useful because"
                }
                """;

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("https://router.huggingface.co/v1/chat/completions")
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(
                        """
                                {
                                  "model": "openai-community/gpt2",
                                  "messages": [
                                    {
                                      "role": "user",
                                      "content": "Java programming is useful because"
                                    }
                                  ],
                                  "max_tokens": 50
                                }
                                """,
                        MediaType.parse("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {

            String result = response.body() != null
                    ? response.body().string()
                    : "";

            if (response.isSuccessful()) {
                System.out.println("Generated Text:");
                System.out.println(result);
            } else {
                System.out.println("Error Code: " + response.code());
                System.out.println(result);
            }
        }
    }
}