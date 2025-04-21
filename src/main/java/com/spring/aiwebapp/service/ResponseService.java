package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.DTO.response.ResponseDTO;
import com.spring.aiwebapp.entity.Chat;
import com.spring.aiwebapp.entity.Prompt;
import com.spring.aiwebapp.entity.Response;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.ChatMapper;
import com.spring.aiwebapp.mapper.ResponseMapper;
import com.spring.aiwebapp.repository.ChatRepository;
import com.spring.aiwebapp.repository.PromptRepository;
import com.spring.aiwebapp.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final AuthService authService;
    private final PromptRepository promptRepository;
    private final ResponseRepository responseRepository;
    private final ChatRepository  chatRepository;


    public ChatDTO saveResponse(String response, Long promptId) {
        Prompt prompt = promptRepository.findById(promptId).orElseThrow(() ->
                new ResourceNotFoundException("Prompt not found with id: " + promptId));

        Response responseEntity = new Response();
        responseEntity.setContent(response);
        responseEntity.setPrompt(prompt);
        responseRepository.save(responseEntity);
        Chat chat = chatRepository.findChatByPromptId(promptId).orElseThrow(() ->
                new ResourceNotFoundException("Chat not found with prompt id: " + promptId));

        return ChatMapper.toDTO(chat);

    }
}
