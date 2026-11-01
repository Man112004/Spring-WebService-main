import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherApi {
    public static void main(String[] args) throws Exception {

        String apiKey = "2db7faae9e48f419c906099ca8a5fd92";

        String url = "https://api.openweathermap.org/data/2.5/weather?q=Ahmedabad,IN&appid="
                + apiKey + "&units=metric";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.body());

        double temp = json.get("main").get("temp").asDouble();
        String description = json.get("weather").get(0)
                .get("description").asText();

        System.out.println("City: Ahmedabad");
        System.out.println("Temperature: " + temp + "°C");
        System.out.println("Weather: " + description);
    }
}