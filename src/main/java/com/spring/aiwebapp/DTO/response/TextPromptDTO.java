package com.spring.aiwebapp.DTO.response;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextPromptDTO {
    private Long id;

    @NotBlank(message = "Content is required")
    private String content;
    private TextResponseDTO response;
    private Long chatId;
}
