package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.DTO.response.TextChatDTO;
import com.spring.aiwebapp.entity.TextChat;
import com.spring.aiwebapp.entity.User;
import java.util.ArrayList;
import java.util.List;

public interface TextChatMap {
    static TextChatDTO textChatToDTO(TextChat textChat){
        return TextChatDTO.builder()
                .id(textChat.getId())
                .title(textChat.getTitle())
                .userId(textChat.getUser().getId())
                .prompts(textChat.getTextPrompt() != null ?
                        textChat.getTextPrompt().stream().map(TextPromptMap::toDTO).toList() : new ArrayList <>())
                .build();
    }

    static List<TextChatDTO> toDTOList(List<TextChat> textChats){
        return textChats.stream().map(TextChatMap::textChatToDTO).toList();
    }

    static TextChat DTOtoEntity(TextChatDTO textChatDTO){
        return TextChat.builder()
                .id(textChatDTO.getId())
                .title(textChatDTO.getTitle())
                .user(User.builder().  id(textChatDTO.getUserId()).build())
                .textPrompt(textChatDTO.getPrompts() != null ?
                        textChatDTO.getPrompts().stream().map(TextPromptMap::toEntity).toList() : new ArrayList <>())
                .build();
    }





}
