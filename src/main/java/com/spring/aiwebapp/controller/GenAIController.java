package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class GenAIController {
    public final ChatService chatService;

    @GetMapping("/ask-ai")
    public Flux<String> getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }



}
