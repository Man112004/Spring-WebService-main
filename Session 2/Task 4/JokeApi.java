
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JokeApi {

    public static <T> T fetchAndParseJson(String url, Class<T> valueType)
            throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response.body(), valueType);
    }

    public static void main(String[] args) throws Exception {

        Joke joke = fetchAndParseJson(
                "https://official-joke-api.appspot.com/random_joke",
                Joke.class
        );

        System.out.println("Setup: " + joke.setup);
        System.out.println("Punchline: " + joke.punchline);
    }
}

class Joke {
    public int id;
    public String type;
    public String setup;
    public String punchline;
}
