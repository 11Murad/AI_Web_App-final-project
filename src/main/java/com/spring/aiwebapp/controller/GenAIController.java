package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.service.ChatService;
import com.spring.aiwebapp.service.ImageService;
import com.spring.aiwebapp.service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/v1/app")
public class GenAIController {
    public final ChatService chatService;
    public final ImageService imageService;
    public final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ask-ai")
    public String getResponse (@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions (@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("/generate-image")
    public void generateImage (HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String url = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(url);
    }


    @GetMapping("/recipe-creator")
    public String createRecipe (@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any" ) String cuisine,
                                @RequestParam(defaultValue = "") String dietaryRestrictions,
                                @RequestParam(defaultValue = "english") String language) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions, language);

    }



}
