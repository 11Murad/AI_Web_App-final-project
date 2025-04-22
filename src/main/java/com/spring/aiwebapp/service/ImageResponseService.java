package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.response.ImageResponseDTO;
import com.spring.aiwebapp.entity.ImagePrompt;
import com.spring.aiwebapp.entity.PictureResponse;
import com.spring.aiwebapp.exception.ResourceNotFoundException;
import com.spring.aiwebapp.mapper.ImageResponseMap;
import com.spring.aiwebapp.repository.ImagePromptRepository;
import com.spring.aiwebapp.repository.ImageResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageResponseService {

    private final AuthService authService;
    private final ImagePromptRepository imagePromptRepository;
    private final ImageResponseRepository imageResponseRepository;

    public List<ImageResponseDTO> saveResponses(List<String> imageUrls, Long promptId) {
        ImagePrompt savedPrompt = imagePromptRepository.findById(promptId).orElseThrow(() ->
                new ResourceNotFoundException("Prompt not found with id: " + promptId));

        List<PictureResponse> responses = imageUrls.stream().map(url -> {
            PictureResponse response = new PictureResponse();
            response.setUrl(url);
            response.setImagePrompt(savedPrompt);
            return imageResponseRepository.save(response); // Repository istifadəsi
        }).toList();
        return ImageResponseMap.toDTOList(responses);
    }
}
