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
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class GenAIController {
    private final AskAIService askAIService;
    private final ImageGenerationService imageGenerationService;

    @PostMapping("/chat/new")
    public ResponseEntity<TextResponseDTO> startChat(@RequestBody String prompt) {
        TextResponseDTO response = askAIService.getResponse(prompt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chats/{id}/continue")
    public ResponseEntity<TextResponseDTO> startWithExistingChat(@RequestParam String prompt, @PathVariable Long id) {
        TextResponseDTO response = askAIService.getResponseWithExistingChat(prompt, id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/image/generate")
    public List<ImageResponseDTO> generateImage (@RequestParam ImagePromptRequest request)    throws IOException {
        return imageGenerationService.processImagePrompt(request);
    }

//    @GetMapping("/generate-image")
//    public List<String> generateImage (@RequestParam String prompt,
//                                       @RequestParam(defaultValue = "hd") String quality,
//                                       @RequestParam(defaultValue = "1") int n,
//                                       @RequestParam(defaultValue = "1024") int height,
//                                       @RequestParam(defaultValue = "1024") int width)    throws IOException {
//        return null;
//    }


}
