package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.entity.Chat;
import com.spring.aiwebapp.entity.User;
import java.util.List;

public interface ChatMapper {
    static ChatDTO toDTO(Chat chat){
        return ChatDTO.builder()
                .id(chat.getId())
                .title(chat.getTitle())
                .userId(chat.getUser().getId())
                .prompts(chat.getPrompts().stream().map(PromptMapper::toDTO).toList())
                .build();
    }

    static List<ChatDTO> toDTOList(List<Chat> chats){
        return chats.stream().map(ChatMapper::toDTO).toList();
    }

    static Chat toEntity(ChatDTO chatDTO){
        return Chat.builder()
                .id(chatDTO.getId())
                .title(chatDTO.getTitle())
                .user(User.builder().id(chatDTO.getUserId()).build())
                .prompts(chatDTO.getPrompts().stream().map(PromptMapper::toEntity).toList())
                .build();
    }

    static void updateChatFromDto(ChatDTO chatDTO, Chat chat) {
        Chat.builder().title(chatDTO.getTitle())
                .prompts(chatDTO.getPrompts().stream().map(PromptMapper::toEntity).toList()).build();
    }



}
