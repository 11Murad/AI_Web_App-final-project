package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.ImageChatDTO;
import com.spring.aiwebapp.service.ImageChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/chat/image")
@RequiredArgsConstructor
public class ImageChatController {
    private final ImageChatService imageChatService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageChatDTO> getChatById(@PathVariable Long id) {
        ImageChatDTO chat = imageChatService.getChatById(id);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/history")
    public ResponseEntity<List<ImageChatDTO>> getRecentChats(@RequestParam(required = false, defaultValue = "10") int page,
                                                             @RequestParam(required = false, defaultValue = "0") int limit) {
        List<ImageChatDTO> chats = imageChatService.getRecentChats(page,limit);
        return ResponseEntity.ok(chats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        imageChatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }

}
