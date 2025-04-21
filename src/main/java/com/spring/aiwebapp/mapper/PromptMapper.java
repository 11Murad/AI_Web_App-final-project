package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.PromptDTO;
import com.spring.aiwebapp.entity.Prompt;

public interface PromptMapper {
    static PromptDTO toDTO(Prompt prompt) {
        return PromptDTO.builder()
                .id(prompt.getId())
                .content(prompt.getContent())
                .chatId(prompt.getChat().getId())
                .response(prompt.getResponse() != null ? ResponseMapper.toDTO(prompt.getResponse()) : null)
                .build();
    }

    static Prompt toEntity(PromptDTO promptDTO) {
        return Prompt.builder()
                .id(promptDTO.getId())
                .content(promptDTO.getContent())
                .chat.getId(promptDTO.getChatId())
                .response(promptDTO.getResponse() != null ? ResponseMapper.toEntity(promptDTO.getResponse()) : null)
                .build();
    }

}
