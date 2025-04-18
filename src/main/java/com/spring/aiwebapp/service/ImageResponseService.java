package com.spring.aiwebapp.service;

import com.spring.aiwebapp.repository.ImagePromptRepository;
import com.spring.aiwebapp.repository.ImageResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageResponseService {

    private final AuthService authService;
    private final ImagePromptRepository imagePromptRepository;
    private final ImageResponseRepository imageResponseRepository;




}
