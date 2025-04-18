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
public class ResponseDTO {
    private Long id;

    private String content;

    private Long promptId;

    private List<String> imageUrl;

}
