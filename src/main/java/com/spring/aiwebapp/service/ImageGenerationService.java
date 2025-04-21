package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.request.ImagePromptRequest;
import com.spring.aiwebapp.DTO.response.*;
import com.spring.aiwebapp.entity.TextChat;
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

    private final OpenAiImageModel imageClient;
    private final ImageChatService imageChatService;
    private final ImagePromptService ImagePromptService;
    private final ImageResponseService responseService;

    private List<String> generateImageByAI(ImagePromptRequest request) {
        ImageResponse response = imageClient.call(
                new ImagePrompt(request.getPrompt(),
                        OpenAiImageOptions.builder().withModel("dall-e-2")
                                .withQuality(request.getQuality())
                                .withN(request.getN())
                                .withHeight(request.getHeight())
                                .withWidth(request.getWidth())
                                .build()
                        )
        );
        return response.getResults().stream().map(result-> result.getOutput().getUrl()).toList();
    }

    public List<ImageResponseDTO> processImagePrompt(ImagePromptRequest request) {
        ImageChatDTO savedChat = imageChatService.createChat(request, TextChat.Type.IMAGE.name());

        // 1. ImagePrompt yarat
        ImagePromptDTO savedPrompt = ImagePromptService.savePrompt(request, savedChat.getId());

        // 2. AI-dan şəkilləri al
        List<String> imageUrls = generateImageByAI(request);

        // 3. Response-ları saxla
        responseService.saveResponses(imageUrls, savedPrompt.getId());

        return imageUrls.stream().map(url -> {
            com.spring.aiwebapp.entity.ImageResponse response = new ImageResponse();
            response.setUrl(url);
            response.setImagePrompt(savedPrompt);
            return imageResponseRepo.save(response); // Repository istifadəsi
        }).toList();
    }



}
