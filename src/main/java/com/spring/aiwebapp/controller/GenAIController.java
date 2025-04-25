package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.request.ImagePromptRequest;
import com.spring.aiwebapp.DTO.response.ImageResponseDTO;
import com.spring.aiwebapp.DTO.response.TextResponseDTO;
import com.spring.aiwebapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/generate")
@RequiredArgsConstructor
public class GenAIController {
    private final AskAIService askAIService;
    private final ImageGenerationService imageGenerationService;

    @PostMapping("/chat")
    public ResponseEntity<TextResponseDTO> startChat(@RequestBody String prompt) {
        TextResponseDTO response = askAIService.getResponse(prompt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chats/{id}")
    public ResponseEntity<TextResponseDTO> startWithExistingChat(@RequestParam String prompt, @PathVariable Long id) {
        TextResponseDTO response = askAIService.getResponseWithExistingChat(prompt, id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/image")
    public List<ImageResponseDTO> generateImage (@RequestBody ImagePromptRequest request) {
        return imageGenerationService.processImagePrompt(request);
    }

}
