package com.spring.aiwebapp.DTO.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextResponseDTO {

    private Long id;

    private String content;

    private Long promptId;

    private Long chatId;
}
