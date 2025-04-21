package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.request.ImagePromptRequest;
import com.spring.aiwebapp.DTO.response.ImageChatDTO;
import com.spring.aiwebapp.entity.ImageChat;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.ImageChatMap;
import com.spring.aiwebapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageChatService {

    private final ImageChatRepository imageChatRepository;
    private final ImagePromptRepository imagePromptRepository;
    private final ImageResponseRepository imageResponseRepository;
    private final AuthService authService;
    private final OpenAiImageModel imageModel;

    public ImageChatDTO getChatById(Long id) {
        User currentUser = authService.getCurrentUser();
        ImageChat imageChat = imageChatRepository.findByIdAndUser(id, currentUser).
                orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + id));

        return ImageChatMap.toDTO(imageChat);
    }

    public List<ImageChatDTO> getUserChats() {
        User currentUser = authService.getCurrentUser();
        List<ImageChat> imageChats = imageChatRepository.findByUserOrderByCreatedAtDesc(currentUser);
        return ImageChatMap.toDTOList(imageChats);
    }

    public List<ImageChatDTO> getRecentChats(int page , int limit) {
        User currentUser = authService.getCurrentUser();
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "updatedAt"));
        List<ImageChat> recentChats = imageChatRepository.findByUser(currentUser,pageRequest).getContent();

        return recentChats.stream().map(ImageChatMap::toDTO).collect(Collectors.toList());
    }

    public ImageChatDTO createChat(ImagePromptRequest request, String type) {
        User currentUser = authService.getCurrentUser();
        String title = request.getPrompt().length() > 40 ? request.getPrompt().substring(0, 37) + "..." : request.getPrompt();

        ImageChat chat = ImageChat.builder()
                .title(title)
                .type(ImageChat.Type.valueOf(type))
                .user(currentUser)
                .build();

        ImageChat savedChat = imageChatRepository.save(chat);

        return ImageChatMap.toDTO(savedChat);
    }

    public void deleteChat(Long id) {
        User currentUser = authService.getCurrentUser();
        if (!imageChatRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chat not found with id: " + id);
        }
        imageChatRepository.deleteByIdAndUser(id, currentUser);
    }

}
