package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.request.ImagePromptRequest;
import com.spring.aiwebapp.DTO.response.ImagePromptDTO;
import com.spring.aiwebapp.entity.ImageChat;
import com.spring.aiwebapp.entity.ImagePrompt;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.ImagePromptMap;
import com.spring.aiwebapp.repository.ImageChatRepository;
import com.spring.aiwebapp.repository.ImagePromptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImagePromptService {

    private final ImageChatRepository imageChatRepository;
    private final ImagePromptRepository imagePromptRepository;
    private final AuthService authService;

    public ImagePromptDTO savePrompt(ImagePromptRequest request, Long chatId) {
        User currentUser = authService.getCurrentUser();
        ImageChat imageChat = imageChatRepository.findByIdAndUser(chatId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + chatId));

        ImagePrompt imagePrompt = ImagePrompt.builder()
                .prompt(request.getPrompt())
                .n(request.getN()).quality(request.getQuality()).width(request.getWidth()).height(request.getHeight())
                .imageChat(imageChat)
                .build();
        return ImagePromptMap.toDTO(imagePromptRepository.save(imagePrompt));
    }

}
