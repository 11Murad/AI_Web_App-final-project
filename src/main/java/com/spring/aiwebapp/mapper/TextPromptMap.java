package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.TextPromptDTO;
import com.spring.aiwebapp.entity.TextPrompt;

public interface TextPromptMap {
    static TextPromptDTO toDTO(TextPrompt textPrompt) {
        return TextPromptDTO.builder()
                .id(textPrompt.getId())
                .content(textPrompt.getContent())
                .chatId(textPrompt.getTextChat().getId())
                .response(textPrompt.getTextResponse() != null ? TextResponseMap.toDTO(textPrompt.getTextResponse()) : null)
                .build();
    }

    static TextPrompt toEntity(TextPromptDTO textPromptDTO) {
        TextPrompt textPrompt = TextPrompt.builder()
                .id(textPromptDTO.getId())
                .content(textPromptDTO.getContent())
                .textResponse(textPromptDTO.getResponse() != null ?
                        TextResponseMap.toEntity(textPromptDTO.getResponse()) : null)
                .build();
        return textPrompt;
    }

}
