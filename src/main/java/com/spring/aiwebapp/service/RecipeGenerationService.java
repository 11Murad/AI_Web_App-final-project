package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeGenerationService {
    private final ChatModel chatModel;

    public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestrictions,
                               String language) {

        ChatDTO savedChat = chatService.createChat(prompt, Chat.Type.RECIPE.name());

        var template = """
                I want to create a recipe using the following ingredients: {ingredients}.
                The cuisine type I prefer is {cuisine}.
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                Please provide me with a detailed recipe including title, list of ingredients, and cooking instructions.
                Please answer me in {language} language.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients", ingredients,
                "cuisine", cuisine,
                "dietaryRestrictions", dietaryRestrictions,
                "language", language
        );
        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();


    }
}