package com.example;

import okhttp3.*;

public class VertexAIDemo {

    public static void main(String[] args) throws Exception {

        String projectId = System.getenv("GOOGLE_CLOUD_PROJECT");
        String accessToken = System.getenv("GOOGLE_ACCESS_TOKEN");
        String location = "Ahmedabad";
        String model = "gemini-2.5-flash";

        if (projectId == null || accessToken == null) {
            System.out.println("Set Google Cloud project and access token.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        String json = """
                {
                  "contents": [
                    {
                      "role": "user",
                      "parts": [
                        {
                          "text": "Explain Java in three simple sentences."
                        }
                      ]
                    }
                  ]
                }
                """;

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json"));

        String url = "https://" + location
                + "-aiplatform.googleapis.com/v1/projects/"
                + projectId
                + "/locations/" + location
                + "/publishers/google/models/"
                + model + ":generateContent";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String result = response.body() != null
                    ? response.body().string()
                    : "";

            if (response.isSuccessful()) {
                System.out.println("Vertex AI Response:");
                System.out.println(result);
            } else {
                System.out.println("Error Code: " + response.code());
                System.out.println(result);
            }
        }
    }
}

/*
 * AI Tool Used: ChatGPT
 * 
 * Prompt given to ChatGPT:
 * "Write simple Java code using OkHttp to call the Google
 * Vertex AI REST API for text generation. Include authentication,
 * a JSON request body, and HTTP error handling."
 * 
 * How I used the output:
 * I used the suggested code structure, configured the Vertex AI
 * endpoint, added the request body and authentication header,
 * and included HTTP status-code checking.
 */