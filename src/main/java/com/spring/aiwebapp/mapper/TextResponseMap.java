package com.spring.aiwebapp.mapper;
import com.spring.aiwebapp.DTO.response.TextResponseDTO;
import com.spring.aiwebapp.entity.TextPrompt;
import com.spring.aiwebapp.entity.TextResponse;

public interface TextResponseMap {
    static TextResponseDTO toDTO(TextResponse textResponse) {
        return TextResponseDTO.builder()
                .id(textResponse.getId())
                .content(textResponse.getContent())
                .promptId(textResponse.getTextPrompt().getId())
                .build();
    }

    static TextResponse toEntity(TextResponseDTO textResponseDTO) {
        return TextResponse.builder()
                .id(textResponseDTO.getId())
                .content(textResponseDTO.getContent())
                .textPrompt(TextPrompt.builder().id(textResponseDTO.getPromptId()).build())
                .build();
    }

}
