package ru.itis.webfluxrabbitmqstudy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import ru.itis.webfluxrabbitmqstudy.client.Client;
import ru.itis.webfluxrabbitmqstudy.entity.Recipe;
import ru.itis.webfluxrabbitmqstudy.entity.RecipeResponse;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Client client;
    private RestTemplate restTemplate;
    private String url;

    public RecipeServiceImpl(Client client, RestTemplate restTemplate, @Value("${recipe.url}") String url) {
        this.client = client;
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public Flux<Recipe> getRecipes(String query) {
        return client.getRecipe(query);
    }

    @Override
    public List<Recipe> getRecipeList(String query) {
        return restTemplate.getForEntity(url + "api?q=" + query, RecipeResponse.class).getBody().getResults();
    }
}
