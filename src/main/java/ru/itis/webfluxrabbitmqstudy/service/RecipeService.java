package ru.itis.webfluxrabbitmqstudy.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.webfluxrabbitmqstudy.entity.Recipe;

import java.util.List;

public interface RecipeService {
    Flux<Recipe> getRecipes(String query);
    List<Recipe> getRecipeList(String query);
}
