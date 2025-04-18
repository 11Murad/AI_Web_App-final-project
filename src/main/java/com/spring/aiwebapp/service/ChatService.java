package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.DTO.response.PromptDTO;
import com.spring.aiwebapp.DTO.response.ResponseDTO;
import com.spring.aiwebapp.entity.Chat;
import com.spring.aiwebapp.entity.Prompt;
import com.spring.aiwebapp.entity.Response;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.ChatMapper;
import com.spring.aiwebapp.repository.ChatRepository;
import com.spring.aiwebapp.repository.PromptRepository;
import com.spring.aiwebapp.repository.ResponseRepository;
import com.spring.aiwebapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final PromptRepository promptRepository;
    private final ResponseRepository responseRepository;
    private final AuthService authService;
    private final OpenAiChatModel chatModel;

    public ChatDTO getChatById(Long id) {
        User currentUser = authService.getCurrentUser();
        Chat chat = chatRepository.findByIdAndUser(id, currentUser).
                orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + id));

        return ChatMapper.toDTO(chat);
    }

    public List<ChatDTO> getUserChats() {
        User currentUser = authService.getCurrentUser();
        List<Chat> chats = chatRepository.findByUserOrderByCreatedAtDesc(currentUser);
        return ChatMapper.toDTOList(chats);
    }

    public List<ChatDTO> getRecentChats(int page , int limit) {
        User currentUser = authService.getCurrentUser();
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "updatedAt"));
        List<Chat> recentChats = chatRepository.findByUser(currentUser,pageRequest).getContent();

        return recentChats.stream()
                .map(ChatMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ChatDTO createChat(String prompt, String type) {
        User currentUser = authService.getCurrentUser();
        String title = prompt.length() > 40 ? prompt.substring(0, 37) + "..." : prompt;

        Chat chat = Chat.builder()
                .title(title)
                .type(Chat.Type.valueOf(type))
                .user(currentUser)
                .build();

        Chat savedChat = chatRepository.save(chat);

        return ChatMapper.toDTO(savedChat);
    }

    public ResponseDTO sendPrompt(Long chatId, PromptDTO promptDTO) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found with id: " + chatId));

        // Create and save prompt
        Prompt prompt = Prompt.builder()
                .content(promptDTO.getContent())
                .chat(chat)
                .build();

        Prompt savedPrompt = promptRepository.save(prompt);

        // Generate response
        String responseContent = chatModel.call(promptDTO.getContent());

        // Create and save response
        Response response = Response.builder()
                .content(responseContent)
                .prompt(savedPrompt)
                .build();

        Response savedResponse = responseRepository.save(response);

        chatRepository.save(chat);

        // Return response DTO
        return ResponseDTO.builder()
                .id(savedResponse.getId())
                .content(savedResponse.getContent())
                .promptId(savedPrompt.getId())
                .build();
    }


    public void deleteChat(Long id) {
        User currentUser = authService.getCurrentUser();
        if (!chatRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chat not found with id: " + id);
        }
        chatRepository.deleteByIdAndUser(id, currentUser);
    }

}
