package com.spring.aiwebapp.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_history")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prompt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String response;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    public Chat() {
        this.createdDate = LocalDateTime.now();
    }

    public Chat(String prompt, String response) {
        this.prompt = prompt;
        this.response = response;
        this.createdDate = LocalDateTime.now();
    }
}
