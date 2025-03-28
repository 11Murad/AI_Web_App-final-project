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

    public List<String> generateImageByOptions(String prompt) {
        ImageResponse response = imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-2")
                                .withQuality("hd")
                                .withN(1)
                                .withHeight(1024)
                                .withWidth(1024)
                                .build()
                        )
        );
        return response.getResults().stream().map(result-> result.getOutput().getUrl()).toList();
    }

}
