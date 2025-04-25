package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.ImagePromptDTO;
import com.spring.aiwebapp.entity.ImageChat;
import com.spring.aiwebapp.entity.ImagePrompt;

import java.util.Collections;

public interface ImagePromptMap {
    static ImagePromptDTO toDTO(ImagePrompt imagePrompt) {
        return ImagePromptDTO.builder()
                .id(imagePrompt.getId())
                .prompt(imagePrompt.getPrompt())
                .n(imagePrompt.getN())
                .height(imagePrompt.getHeight())
                .width(imagePrompt.getWidth())
                .response(
                    imagePrompt.getResponses() != null
                        ? ImageResponseMap.toDTOList(imagePrompt.getResponses())
                        : new java.util.ArrayList<>()
                )
                .build();
    }

    static ImagePrompt toEntity(ImagePromptDTO imagePromptDTO) {
        return ImagePrompt.builder()
                .id(imagePromptDTO.getId())
                .prompt(imagePromptDTO.getPrompt())
                .responses(Collections.emptyList())
                .build();
    }
}
