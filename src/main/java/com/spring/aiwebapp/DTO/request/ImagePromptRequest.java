package com.spring.aiwebapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.aiwebapp.validation.AllowedImageSize;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Min(1)
    @Max(10)
    private int n = 1;

    @Builder.Default
    @NotNull
    @AllowedImageSize
    private int height = 1024;

    @Builder.Default
    @NotNull
    @AllowedImageSize
    private int width = 1024;
}
