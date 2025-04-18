package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class GenAIController {
    private final AskAIService askAIService;
    private final ImageGenerationService imageGenerationService;
    private final RecipeGenerationService recipeGenerationService;
    private final ChatService chatService;


    @GetMapping("/chat/new")
    public ResponseEntity<ChatDTO> startChat(@RequestBody String prompt) {
        ChatDTO response = askAIService.getResponse(prompt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chat/continue/{id}")
    public ResponseEntity<ChatDTO> startWithExistingChat(@RequestParam String prompt, @PathVariable Long id) {
        ChatDTO response = askAIService.getResponseWithExistingChat(prompt, id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/generate-image")
    public List<String> generateImage (@RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") int n,
                                       @RequestParam(defaultValue = "1024") int height,
                                       @RequestParam(defaultValue = "1024") int width)    throws IOException {

        List<String> imageUrls = imageGenerationService.generateImageByOptions(prompt, quality, n, height, width);

        return imageUrls;
    }


    @GetMapping("/recipe-creator")
    public String createRecipe (@RequestParam String ingredients,// salam
                                @RequestParam(defaultValue = "any" ) String cuisine,
                                @RequestParam(defaultValue = "") String dietaryRestrictions,
                                @RequestParam(defaultValue = "english") String language) {
        return recipeGenerationService.createRecipe(ingredients, cuisine, dietaryRestrictions, language);

    }



}
