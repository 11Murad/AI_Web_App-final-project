package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.DTO.response.PromptDTO;
import com.spring.aiwebapp.entity.Chat;
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
    private final PromptService promptService;
    private final ResponseService responseService;

    public ChatDTO getResponse(String prompt) {
        ChatDTO savedChat = chatService.createChat(prompt, Chat.Type.TEXT.name());
        PromptDTO savedPrompt = promptService.savePrompt(prompt, savedChat.getId());
        String aiResponse = response(prompt);

        ChatDTO chatDTO = responseService.saveResponse(aiResponse, savedPrompt.getId()); ;
        return chatDTO;
    }

    public ChatDTO getResponseWithExistingChat(String prompt, Long chatId) {
        PromptDTO savedPrompt = promptService.savePrompt(prompt, chatId);
        String aiResponse = response(prompt);

        ChatDTO chatDTO = responseService.saveResponse(aiResponse, savedPrompt.getId()); ;
        return chatDTO;
    }

    private String response(String prompt) {
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
