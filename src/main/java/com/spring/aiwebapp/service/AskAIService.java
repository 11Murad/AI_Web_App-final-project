package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.response.TextChatDTO;
import com.spring.aiwebapp.DTO.response.TextPromptDTO;
import com.spring.aiwebapp.DTO.response.TextResponseDTO;
import com.spring.aiwebapp.entity.TextChat;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskAIService {

    private final ChatModel chatModel;
    private final ChatService chatService;
    private final TextPromptService textPromptService;
    private final TextResponseService textResponseService;

    public TextResponseDTO getResponse(String prompt) {
        TextChatDTO savedChat = chatService.createChat(prompt, TextChat.Type.TEXT.name());
        TextPromptDTO savedPrompt = textPromptService.savePrompt(prompt, savedChat.getId());
        String aiResponse = responseFromAI(prompt);

        TextResponseDTO response = textResponseService.saveResponse(aiResponse, savedPrompt.getId()); ;
        return response;
    }

    public TextResponseDTO getResponseWithExistingChat(String prompt, Long chatId) {
        TextPromptDTO savedPrompt = textPromptService.savePrompt(prompt, chatId);
        String aiResponse = responseFromAI(prompt);

        TextResponseDTO response = textResponseService.saveResponse(aiResponse, savedPrompt.getId()); ;
        return response;
    }

    private String responseFromAI(String prompt) {
        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-4o-mini")
                                .temperature(0.4)
                                .build()
                ));
        return response.getResult().getOutput().getText();
    }
}
