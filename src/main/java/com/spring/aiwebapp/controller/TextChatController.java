package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.TextChatDTO;
import com.spring.aiwebapp.service.TextChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/chat/text")
@RequiredArgsConstructor
public class TextChatController {
    private final TextChatService textChatService;

    @GetMapping("/{id}")
    public ResponseEntity<TextChatDTO> getChatById(@PathVariable Long id) {
        TextChatDTO chat = textChatService.getChatById(id);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TextChatDTO>> getRecentChats(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "10") int limit) {
        List<TextChatDTO> chats = textChatService.getRecentChats(page,limit);
        return ResponseEntity.ok(chats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        textChatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }


}
