package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.service.ImageChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/chat/image")
@RequiredArgsConstructor
public class ImageChatController {
    private final ImageChatService imageChatService;


}
