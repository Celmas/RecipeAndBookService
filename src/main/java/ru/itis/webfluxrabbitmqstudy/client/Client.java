package ru.itis.webfluxrabbitmqstudy.client;

import reactor.core.publisher.Flux;
import ru.itis.webfluxrabbitmqstudy.entity.Recipe;
import ru.itis.webfluxrabbitmqstudy.entity.RecipeResponse;

public interface Client {
    Flux<Recipe> getRecipe(String query);
}
