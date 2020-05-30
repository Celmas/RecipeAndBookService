package ru.itis.webfluxrabbitmqstudy.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.itis.webfluxrabbitmqstudy.entity.BlacklistPerson;
import ru.itis.webfluxrabbitmqstudy.entity.Recipe;
import ru.itis.webfluxrabbitmqstudy.entity.dto.SendRecipeRequest;
import ru.itis.webfluxrabbitmqstudy.service.BlacklistService;
import ru.itis.webfluxrabbitmqstudy.service.EmailService;
import ru.itis.webfluxrabbitmqstudy.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    private RecipeService recipeService;
    private EmailService recipeEmailService;
    private EmailService blackListEmailService;
    private BlacklistService blacklistService;

    public RecipeController(RecipeService service, @Qualifier("recipeEmailServiceImpl") EmailService recipeEmailService, @Qualifier("blacklistEmailServiceImpl") EmailService blackListEmailService, BlacklistService blacklistService) {
        this.recipeService = service;
        this.recipeEmailService = recipeEmailService;
        this.blackListEmailService = blackListEmailService;
        this.blacklistService = blacklistService;
    }

    @GetMapping("/recipe")
    public Flux<Recipe> getRecipe(@RequestParam String q) {
        return recipeService.getRecipes(q);
    }

    @PostMapping("/recipe/email")
    @ResponseBody
    public boolean sendRecipeByEmail(SendRecipeRequest request) {
        List<Recipe> recipes = recipeService.getRecipeList(request.getQuery());
        Optional<BlacklistPerson> candidate = blacklistService.findByEmail(request.getEmail());
        candidate.ifPresent(blacklistPerson -> blackListEmailService.sendEmail("blacklist@test.com",
                blacklistPerson.getName() + " " + blacklistPerson.getSurname()));
        return recipeEmailService.sendEmail(request.getEmail(), recipes.stream()
                .map(Recipe::getHref)
                .collect(Collectors.joining("\n")));

    }

}
