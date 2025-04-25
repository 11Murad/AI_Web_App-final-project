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
        String title = prompt.length() > 37 ? prompt.substring(0, 33) + "..." : prompt;

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

}
