package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.TextChatDTO;
import com.spring.aiwebapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("v1/api/chat/text")
@RequiredArgsConstructor
public class TextChatController {
    private final ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<TextChatDTO> getChatById(@PathVariable Long id) {
        TextChatDTO chat = chatService.getChatById(id);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/user/")
    public ResponseEntity<List<TextChatDTO>> getUserChats() {
        List<TextChatDTO> chats = chatService.getUserChats();
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<TextChatDTO>> getRecentChats(@RequestParam(required = false, defaultValue = "10") int limit,
                                                            @RequestParam(required = false, defaultValue = "0") int page) {
        List<TextChatDTO> chats = chatService.getRecentChats(page,limit);
        return ResponseEntity.ok(chats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }


}
