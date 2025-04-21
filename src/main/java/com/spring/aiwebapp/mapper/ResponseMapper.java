package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.ResponseDTO;
import com.spring.aiwebapp.entity.Response;

public interface ResponseMapper {
    static ResponseDTO toDTO(Response response) {
        return ResponseDTO.builder()
                .id(response.getId())
                .content(response.getContent())
                .promptId(response.getPrompt().getId())
                .build();
    }

    static Response toEntity(ResponseDTO responseDTO) {
        return Response.builder()
                .id(responseDTO.getId())
                .content(responseDTO.getContent())
                .prompt.getId(responseDTO.getPromptId())
                .build();
    }

}
