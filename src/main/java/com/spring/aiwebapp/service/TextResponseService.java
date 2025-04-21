package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.response.TextResponseDTO;
import com.spring.aiwebapp.entity.TextPrompt;
import com.spring.aiwebapp.entity.TextResponse;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.TextResponseMap;
import com.spring.aiwebapp.repository.TextPromptRepository;
import com.spring.aiwebapp.repository.TextResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextResponseService {
    private final AuthService authService;
    private final TextPromptRepository textPromptRepository;
    private final TextResponseRepository textResponseRepository;


    public TextResponseDTO saveResponse(String response, Long promptId) {
        TextPrompt textPrompt = textPromptRepository.findById(promptId).orElseThrow(() ->
                new ResourceNotFoundException("Prompt not found with id: " + promptId));

        TextResponse textResponseEntity = new TextResponse();
        textResponseEntity.setContent(response);
        textResponseEntity.setTextPrompt(textPrompt);

        return TextResponseMap.toDTO(textResponseRepository.save(textResponseEntity));

    }
}
