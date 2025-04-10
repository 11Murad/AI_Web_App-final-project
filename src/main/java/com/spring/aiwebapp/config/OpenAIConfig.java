package com.spring.aiwebapp.config;

import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAiChatOptions openAiChatOptions() {
        return OpenAiChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.4)
                .maxTokens(700)
                .build();
    }

    @Bean
    public OpenAiImageOptions openAiImageOptions() {
        return OpenAiImageOptions.builder()
                .model("dall-e-2")
                .build();
    }

}
