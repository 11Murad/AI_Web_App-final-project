package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.entity.Chat;
import com.spring.aiwebapp.service.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/app")
@RequiredArgsConstructor
public class GenAIController {
    private final AskAIService askAIService;
    private final ImageGenerationService imageGenerationService;
    private final RecipeGenerationService recipeGenerationService;
    private final ChatService chatService;
    private final PromptService promptService;

    @GetMapping("/chat/new")
    public ResponseEntity<String> startChat(@Valid @RequestBody String prompt) {
        ChatDTO chat = chatService.createChat(prompt, Chat.Type.TEXT.name());
        promptService.savePrompt(prompt, chat.getId());


        return ResponseEntity.ok(response);
    }

    @GetMapping("/ask-ai")
    public String getResponseByOptions(@RequestParam String prompt) {
        return askAIService.getResponseByOptions(prompt);
    }

    @GetMapping("/generate-images")
    public List<String> generateImage (HttpServletResponse response,
                                       @RequestParam String prompt,
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
