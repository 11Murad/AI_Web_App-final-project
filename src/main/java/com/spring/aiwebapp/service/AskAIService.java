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

    public String getResponseByOptions(String prompt) {
        ChatDTO savedChat = chatService.createChat(prompt, Chat.Type.TEXT.name());
        PromptDTO savedPrompt = promptService.savePrompt(prompt, savedChat.getId());

        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-4o-mini")
                                .temperature(0.4)
                                .build()
                ));
        String savedResponse = response.getResult().getOutput().getText();
        responseService.saveResponse(response, prompt);

        //ResponseServis de promptId, chatId save edirik


        return savedResponse;
    }
}
