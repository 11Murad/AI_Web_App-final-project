package com.spring.aiwebapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Builder.Default
    private String quality = "standard";

    @Builder.Default
    private int n = 1;

    @Builder.Default
    private int height = 1024;

    @Builder.Default
    private int width = 1024;

    @JsonIgnore
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
