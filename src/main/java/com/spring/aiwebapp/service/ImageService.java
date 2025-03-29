package com.spring.aiwebapp.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageService {

    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt) {
        return imageModel.call(
                new ImagePrompt(prompt)
        );
    }

    public List<String> generateImageByOptions(String prompt, String quality,
                                               int n, int height, int width) {
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
