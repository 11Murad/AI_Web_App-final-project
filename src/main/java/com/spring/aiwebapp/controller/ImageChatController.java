package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.ImageChatDTO;
import com.spring.aiwebapp.service.ImageChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/api/chat/image")
@RequiredArgsConstructor
public class ImageChatController {
    private final ImageChatService imageChatService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageChatDTO> getChatById(@PathVariable Long id) {
        ImageChatDTO chat = imageChatService.getChatById(id);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/history")
    public ResponseEntity<List<ImageChatDTO>> getUserChats() {
        List<ImageChatDTO> chats = imageChatService.getUserChats();
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<ImageChatDTO>> getRecentChats(@RequestParam(required = false, defaultValue = "10") int limit,
                                                            @RequestParam(required = false, defaultValue = "0") int page) {
        List<ImageChatDTO> chats = imageChatService.getRecentChats(page,limit);
        return ResponseEntity.ok(chats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        imageChatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }

}
