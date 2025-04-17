package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageGenerationService {

    private final OpenAiImageModel imageModel;
    private final ChatService chatService;

    public List<String> generateImageByOptions(String prompt, String quality,
                                               int n, int height, int width) {
        ChatDTO savedChat = chatService.createChat(prompt, Chat.Type.IMAGE.name());


        ImageResponse response = imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-2")
                                .withQuality(quality)
                                .withN(n)
                                .withHeight(height)
                                .withWidth(width)
                                .build()
                        )
        );
        return response.getResults().stream().map(result-> result.getOutput().getUrl()).toList();
    }



}
