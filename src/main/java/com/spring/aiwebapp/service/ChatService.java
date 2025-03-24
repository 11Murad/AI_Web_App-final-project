package com.spring.aiwebapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final StreamingChatModel chatModel;

    public Flux<String> getResponse (String prompt) {
        return chatModel.stream(prompt);
    }
}
