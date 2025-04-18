package com.spring.aiwebapp.DTO.request;

import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagePromptRequest {

    @NotBlank(message = "Prompt is required")
    private String prompt;

    private String quality = "HD";
    private int n = 1;
    private int height = 1024;
    private int width = 1024;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
