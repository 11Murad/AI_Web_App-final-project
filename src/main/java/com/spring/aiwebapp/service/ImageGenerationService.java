package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.request.ImagePromptRequest;
import com.spring.aiwebapp.DTO.response.*;
import com.spring.aiwebapp.entity.TextChat;
import com.spring.aiwebapp.repository.ImageResponseRepository;
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
    private final ImageResponseRepository imageResponseRepo;

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

        // 1. ImagePrompt saxla
        ImagePromptDTO savedPrompt = ImagePromptService.savePrompt(request, savedChat.getId());

        // 2. AI-dan şəkilləri al
        List<String> imageUrls = generateImageByAI(request);

        // 3. Response-ları saxla
        List<ImageResponseDTO> responses = responseService.saveResponses(imageUrls, savedPrompt.getId());

        return responses;
    }
}
