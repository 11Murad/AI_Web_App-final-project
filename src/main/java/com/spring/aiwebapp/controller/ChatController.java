package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.response.ChatDTO;
import com.spring.aiwebapp.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("v1/api/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

//    @PostMapping
//    public ResponseEntity<ChatDTO> createChat(@Valid @RequestBody String prompt) {
//
//        return new ResponseEntity<>(chat, HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDTO> getChatById(@PathVariable Long id) {
        ChatDTO chat = chatService.getChatById(id);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/user/chats")
    public ResponseEntity<List<ChatDTO>> getUserChats() {
        List<ChatDTO> chats = chatService.getUserChats();
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<ChatDTO>> getRecentChats(@RequestParam(required = false, defaultValue = "10") int limit,
                                                        @RequestParam(required = false, defaultValue = "0") int page) {
        List<ChatDTO> chats = chatService.getRecentChats(page,limit);
        return ResponseEntity.ok(chats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }


}
