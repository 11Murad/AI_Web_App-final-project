package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.response.TextPromptDTO;
import com.spring.aiwebapp.entity.TextChat;
import com.spring.aiwebapp.entity.TextPrompt;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.TextPromptMap;
import com.spring.aiwebapp.repository.TextChatRepository;
import com.spring.aiwebapp.repository.TextPromptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextPromptService {
    private final TextChatRepository textChatRepository;
    private final TextPromptRepository textPromptRepository;
    private final AuthService authService;

    public TextPromptDTO savePrompt(String prompt, Long chatId) {
        User currentUser = authService.getCurrentUser();
        TextChat textChat = textChatRepository.findByIdAndUser(chatId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + chatId));

        TextPrompt textPrompt = new TextPrompt();
        textPrompt.setContent(prompt);
        textPrompt.setTextChat(textChat);
        return TextPromptMap.toDTO(textPromptRepository.save(textPrompt));
    }


}
