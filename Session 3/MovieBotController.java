
package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieBotController {

    @Value("${openai.api.key}")
    private String apiKey;

    @PostMapping("/askMovieBot")
    public Map askMovieBot(@RequestBody Map<String, String> data) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
            "model", "gpt-4o-mini",
            "messages", List.of(
                Map.of(
                    "role", "user",
                    "content", data.get("question")
                )
            )
        );

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        return rt.postForObject(
            "https://api.openai.com/v1/chat/completions",
            request,
            Map.class
        );
    }
}
