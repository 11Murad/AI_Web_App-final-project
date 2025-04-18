package com.spring.aiwebapp.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextChatDTO {

    private Long id;

    private String title;

    private Long userId;

    private List<TextPromptDTO> prompts;
}
