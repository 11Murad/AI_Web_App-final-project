package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.ImagePromptDTO;
import com.spring.aiwebapp.entity.ImagePrompt;

public interface ImagePromptMap {
    static ImagePromptDTO toDTO(ImagePrompt imagePrompt) {
        return ImagePromptDTO.builder()
                .id(imagePrompt.getId())
                .prompt(imagePrompt.getPrompt())
                .imageChatId(imagePrompt.getImageChat().getId())
                .build();
    }
}
