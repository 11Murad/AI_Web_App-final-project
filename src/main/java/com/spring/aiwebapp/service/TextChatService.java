package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.response.TextChatDTO;
import com.spring.aiwebapp.entity.TextChat;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.TextChatMap;
import com.spring.aiwebapp.repository.TextChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TextChatService {
    private final TextChatRepository textChatRepository;
    private final AuthService authService;

    public TextChatDTO getChatById(Long id) {
        User currentUser = authService.getCurrentUser();
        TextChat textChat = textChatRepository.findByIdAndUser(id, currentUser).
                orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + id));
        return TextChatMap.textChatToDTO(textChat);
    }

    public List<TextChatDTO> getUserChats() {
        User currentUser = authService.getCurrentUser();
        List<TextChat> textChats = textChatRepository.findByUserOrderByCreatedAtDesc(currentUser);
        return TextChatMap.toDTOList(textChats);
    }

    public List<TextChatDTO> getRecentChats(int page , int limit) {
        User currentUser = authService.getCurrentUser();
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "updatedAt"));
        List<TextChat> recentTextChats = textChatRepository.findByUser(currentUser,pageRequest).getContent();

        return recentTextChats.stream()
                .map(TextChatMap::textChatToDTO)
                .collect(Collectors.toList());
    }

    public TextChatDTO createChat(String prompt, String type) {
        User currentUser = authService.getCurrentUser();
        String title = prompt.length() > 40 ? prompt.substring(0, 37) + "..." : prompt;

        TextChat textChat = TextChat.builder()
                .title(title)
                .type(TextChat.Type.valueOf(type))
                .user(currentUser)
                .build();

        TextChat savedTextChat = textChatRepository.save(textChat);
        return TextChatMap.textChatToDTO(savedTextChat);
    }

    public void deleteChat(Long id) {
        User currentUser = authService.getCurrentUser();
        if (!textChatRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chat not found with id: " + id);
        }
        textChatRepository.deleteByIdAndUser(id, currentUser);
    }
//
//    public TextResponseDTO sendPrompt(Long chatId, TextPromptDTO textPromptDTO) {
//        TextChat textChat = textChatRepository.findById(chatId)
//                .orElseThrow(() -> new EntityNotFoundException("Chat not found with id: " + chatId));
//
//        // Create and save prompt
//        TextPrompt textPrompt = TextPrompt.builder()
//                .content(textPromptDTO.getContent())
//                .textChat(textChat)
//                .build();
//
//        TextPrompt savedTextPrompt = textPromptRepository.save(textPrompt);
//
//        // Generate response
//        String responseContent = chatModel.call(textPromptDTO.getContent());
//
//        // Create and save response
//        TextResponse textResponse = TextResponse.builder()
//                .content(responseContent)
//                .textPrompt(savedTextPrompt)
//                .build();
//
//        TextResponse savedTextResponse = textResponseRepository.save(textResponse);
//
//        textChatRepository.save(textChat);
//
//        // Return response DTO
//        return TextResponseDTO.builder()
//                .id(savedTextResponse.getId())
//                .content(savedTextResponse.getContent())
//                .promptId(savedTextPrompt.getId())
//                .build();

//    }

}
