package com.spring.aiwebapp.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDTO {

    private Long id;

    private String url;

    private Long promptId;
}
