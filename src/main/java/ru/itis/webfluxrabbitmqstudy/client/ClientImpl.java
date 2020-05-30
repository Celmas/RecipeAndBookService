package ru.itis.webfluxrabbitmqstudy.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.webfluxrabbitmqstudy.entity.Recipe;
import ru.itis.webfluxrabbitmqstudy.entity.RecipeResponse;

import java.nio.charset.StandardCharsets;

@Component
public class ClientImpl implements Client {

    private WebClient client;

    public ClientImpl(@Value("${recipe.url}") String url) {
        this.client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.customCodecs().decoder(
                                new Jackson2JsonDecoder(new ObjectMapper(),
                                        new MimeType("text", "javascript", StandardCharsets.UTF_8)))).build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<Recipe> getRecipe(String query) {
        return client
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/")
                        .queryParam("q", query)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(RecipeResponse.class))
                .flatMapIterable(RecipeResponse::getResults);
    }
}
