package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.DTO.response.PromptDTO;
import com.spring.aiwebapp.entity.Chat;
import com.spring.aiwebapp.entity.Prompt;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.ChatMapper;
import com.spring.aiwebapp.mapper.PromptMapper;
import com.spring.aiwebapp.repository.ChatRepository;
import com.spring.aiwebapp.repository.PromptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromptService {
    private final ChatRepository chatRepository;
    private final PromptRepository promptRepository;
    private final ChatService chatService;
    private final AuthService authService;

    public PromptDTO savePrompt(String prompt, Long chatId) {
        User currentUser = authService.getCurrentUser();
        Chat chat = chatRepository.findByIdAndUser(chatId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + chatId));

        Prompt promptEntity = new Prompt();
        promptEntity.setContent(prompt);
        promptEntity.setChat(chat);
        return PromptMapper.toDTO(promptRepository.save(promptEntity));
    }


}
