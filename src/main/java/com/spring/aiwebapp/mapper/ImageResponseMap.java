package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.ImageResponseDTO;
import com.spring.aiwebapp.entity.ImagePrompt;
import com.spring.aiwebapp.entity.PictureResponse;

import java.util.List;

public interface ImageResponseMap {
    static ImageResponseDTO toDTO(PictureResponse pictureResponse) {
        return ImageResponseDTO.builder()
                .id(pictureResponse.getId())
                .url(pictureResponse.getUrl())
                .promptId(pictureResponse.getImagePrompt().getId())
                .build();
    }

    static PictureResponse toEntity(ImageResponseDTO imageResponseDTO) {
        return PictureResponse.builder()
                .id(imageResponseDTO.getId())
                .url(imageResponseDTO.getUrl())
                .imagePrompt(ImagePrompt.builder().id(imageResponseDTO.getPromptId()).build())
                .build();
    }

    static List<ImageResponseDTO> toDTOList(List<PictureResponse> responses) {
        return responses.stream().map(ImageResponseMap::toDTO).toList();
    }

}
