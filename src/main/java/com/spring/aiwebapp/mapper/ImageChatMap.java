package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.ImageChatDTO;
import com.spring.aiwebapp.entity.ImageChat;
import java.util.ArrayList;
import java.util.List;

public interface ImageChatMap {

    static ImageChatDTO toDTO(ImageChat imageChat) {
        return ImageChatDTO.builder()
                .id(imageChat.getId())
                .title(imageChat.getTitle())
                .userId(imageChat.getUser().getId())
                .prompts(imageChat.getImagePrompts() != null ?
                        imageChat.getImagePrompts().stream().map(ImagePromptMap::toDTO).toList() : new ArrayList<>())
                .build();
    }

    static List<ImageChatDTO> toDTOList(List<ImageChat> imageChats){
        return imageChats.stream().map(ImageChatMap::toDTO).toList();
    }


}
